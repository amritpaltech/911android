package com.soft.credit911.ui.documnet

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.chuzi.utils.URIPathHelper
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ing.quiz.network.RestClient
import com.ing.quiz.ui.base_classes.BaseActivity
import com.scanlibrary.ScanActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.adaptor.DocumentDetailsAdapter
import com.soft.credit911.adaptor.OtherDocAdap
import com.soft.credit911.datamodel.data_docs
import com.soft.credit911.dialog.DialogCamera
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class DocumentActivity : BaseActivity() {

    private var documentDetailsAdapter: DocumentDetailsAdapter? = null
    private var otherDocAdaptor: OtherDocAdap? = null
    private var documentRequred = ArrayList<data_docs.DocData>()
    private var documentOther = ArrayList<data_docs.DocData>()
    val viewModel=DocumentViewModel()
    var selectedDoc:data_docs.DocData?=null
    var bodyImgeThumb: ArrayList<MultipartBody.Part>? = ArrayList<MultipartBody.Part>()
    override fun getLayoutID(): Int {
       return  R.layout.activity_document
    }

    override fun onViewCreated() {
   
        toolbarTitle.text = "Document"
        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        init()
    }

    fun init(){
        viewModel.getDocuments()
        attachObserver()
    }

    fun attachObserver() {
        viewModel?.apiError.observe(this, androidx.lifecycle.Observer {
            CommonUtils.showdialog(it.toString(), this, false)
        })

        viewModel?.isLoading?.observe(this, androidx.lifecycle.Observer {

            if (it) {
                showProgress()
            } else {
                hideProgress()
            }
        })


        viewModel?.dataDocs.observe(this, androidx.lifecycle.Observer {
           if(it.status.equals("success")){
               documentOther.clear()
               documentRequred.clear()
               if(it?.documents?.other!=null) {
                   documentOther = it?.documents?.other

               }
               if(it?.documents?.required!=null) {
                   documentRequred = it?.documents?.required

               }
               setLListdata()
           }
        })

        viewModel?.dataDocsUpload.observe(this, androidx.lifecycle.Observer {
            if(it.status.equals("success")){
                it.message?.let { it1 -> createAlert(it1) }
                documentOther.clear()
                documentRequred.clear()
                if(it?.documents?.other!=null) {
                    documentOther = it?.documents?.other

                }
                if(it?.documents?.required!=null) {
                    documentRequred = it?.documents?.required

                }
                setLListdata()
            }
        })
    }

    fun setLListdata(){
        otherDocAdaptor = OtherDocAdap(documentOther){
            handleDocumentClick(it)
        }
        rv_other_document.adapter=otherDocAdaptor

        val requireAdap=OtherDocAdap(documentRequred){
            handleDocumentClick(it)
        }
        rv_documentCompulsary.adapter=requireAdap
        var completedDoc=0
        for(iDoc in documentRequred){
            if(iDoc.status.equals("approved")){
                completedDoc=completedDoc+1
            }
        }

        var otherDocDoneC=0
        for(iDoc in documentOther){
            if(iDoc.status.equals("approved")){
                otherDocDoneC=otherDocDoneC+1
            }
        }
        completedTextOther.text="Tasks Completed "+otherDocDoneC+" of "+documentOther.size
        completedText.text="Tasks Completed "+completedDoc+" of "+documentRequred.size
    }



    fun handleDocumentClick(selectedDoc: data_docs.DocData){
        this.selectedDoc=selectedDoc
        if(selectedDoc.action.equals("upload-document")){


                val dialog= DialogCamera(){op->
               when(op){
                   1->{
                       ImagePicker.with(this)
                           .cameraOnly()  			//Crop image(Optional), Check Customization for more option
                           .compress(1024)			//Final image size will be less than 1 MB(Optional)
                           .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                           .start()
                   }
                   2->{
                       ImagePicker.with(this)
                            .galleryOnly()   			//Crop image(Optional), Check Customization for more option
                           .compress(1024)			//Final image size will be less than 1 MB(Optional)
                           .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                           .start()
                   }

                   3->{
                       FilePickerBuilder.instance
                           .setMaxCount(1) //optional
//                    .setSelectedFiles(filePaths) //optional
                           .setActivityTheme(R.style.LibAppTheme) //optional
                           .pickFile(this, 3000);
                   }
               }

                }
                dialog.show(supportFragmentManager, "options")
            }
        else if(selectedDoc.action.equals("uploaded")){
            CommonUtils.showdialog(if(selectedDoc.message!=null) selectedDoc.message else "Your document under review. Please wait!",
                this, false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {


            if(requestCode== ImagePicker.REQUEST_CODE) {
                try {
                    val uriPathHelper = URIPathHelper()
                    val selectedVideoPath = data?.data?.let { uriPathHelper.getPath(this, it) }
                    val thizIntent = Intent(
                        applicationContext,
                        ScanActivity::class.java
                    )
                    thizIntent.putExtra("Image", selectedVideoPath)
                    startActivityForResult(thizIntent,1234)
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
            else if (requestCode== 1234 && resultCode== Activity.RESULT_OK )
            {
                val byteArray = data?.getByteArrayExtra("path")
                val file = File(getVideoFilePath(this));
                val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
                try {
                    FileOutputStream(file.path).use { out ->
                        bmp.compress(
                            Bitmap.CompressFormat.JPEG,
                            100,
                            out
                        ) // bmp is your Bitmap instance
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val requestFile = RequestBody.create((getContentResolver().
                getType(Uri.parse(file.path))?.toMediaTypeOrNull()), file!!)
                bodyImgeThumb?.add(
                    MultipartBody.Part.createFormData(
                        "file",
                        file?.name,
                        requestFile
                    )
                )
                uploadData()
            }
            else if (requestCode== 3000 && resultCode== Activity.RESULT_OK )
            {
                try {
                    val dataList: ArrayList<Uri> =
                        data?.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS)!!
                    if (dataList != null) {
                        val uriPathHelper = URIPathHelper()
                        val selectedVideoPath = uriPathHelper.getPath(this, dataList.get(0))
                        var file= File(selectedVideoPath)
                        if(file.isFile){
                            val requestFile = RequestBody.create((getContentResolver().
                            getType(dataList.get(0)))?.toMediaTypeOrNull(), file!!)
                            bodyImgeThumb?.add(
                                MultipartBody.Part.createFormData(
                                    "file",
                                    file?.name,
                                    requestFile
                                )
                            )
                            uploadData()
                        }

                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {

        }
    }

    fun uploadData(){
        val ob = HashMap<String, RequestBody>()
        ob.put("title", RestClient.createRequestBody(selectedDoc?.Label!!.trim()))
        ob.put("document__id", RestClient.createRequestBody(selectedDoc?.doc_id!!.trim()))
        ob.put("doc_type", RestClient.createRequestBody(selectedDoc?.document_type.toString().trim()))
        ob.put("case_id", RestClient.createRequestBody(selectedDoc?.case_id.toString().trim()))
        ob.put("fk_documentrequest_id", RestClient.createRequestBody(selectedDoc?.fk_documentrequest_id.toString().trim()))
        ob.put("notes", RestClient.createRequestBody(selectedDoc?.notes.toString().trim()))
        bodyImgeThumb?.let { viewModel.uploadDocument(ob, it) }
    }

    companion object {
        private const val SELECT_PICTURES = 0
        private const val PICK_FROM_CAMERA = 1
    }

    fun getVideoFilePath(context: Context): String {
        val dir = context.getExternalFilesDir(null)
        return ((if (dir == null) "" else dir.absolutePath + "/")
                + System.currentTimeMillis() + ".png")
    }

    fun createAlert(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}