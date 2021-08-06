package com.soft.credit911.ui.documnet

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ing.quiz.ui.base_classes.BaseActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.adaptor.DocumentDetailsAdapter
import com.soft.credit911.adaptor.OtherDocAdap
import com.soft.credit911.datamodel.data_docs
import com.soft.credit911.dialog.DialogCamera
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class DocumentActivity : BaseActivity() {

    private var documentDetailsAdapter: DocumentDetailsAdapter? = null
    private var otherDocAdaptor: OtherDocAdap? = null
    private var documentRequred = ArrayList<data_docs.DocData>()
    private var documentOther = ArrayList<data_docs.DocData>()
    val viewModel=DocumentViewModel()
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
        for(i in documentRequred){
            if(i.status.equals("completed")){
                completedDoc=completedDoc+1
            }
        }
        completedText.text="Task comleted "+completedDoc+" of "+documentRequred.size
    }

    fun handleDocumentClick(dataDocs: data_docs.DocData){

        when(dataDocs.status){
            "missing"->{

                val dialog= DialogCamera(){op->
               when(op){
                   1->{
                       ImagePicker.with(this)
                           .crop()	  .cameraOnly()  			//Crop image(Optional), Check Customization for more option
                           .compress(1024)			//Final image size will be less than 1 MB(Optional)
                           .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                           .start()
                   }
                   2->{
                       ImagePicker.with(this)
                           .crop()	 .galleryOnly()   			//Crop image(Optional), Check Customization for more option
                           .compress(1024)			//Final image size will be less than 1 MB(Optional)
                           .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                           .start()
                   }

                   3->{

                   }
               }

                }
                dialog.show(supportFragmentManager, "options")
            }
            "pending"->{
                CommonUtils.showdialog("Please wait while we verify your document", this, false)
            }
            "approved"->{
                CommonUtils.showdialog("Document you provided is approved", this, false)
            }
            "rejected"->{
                CommonUtils.showdialog("Document you provided is rejected:", this, false)
            }

        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SELECT_PICTURES -> if (resultCode == RESULT_OK) {
                if (data!!.clipData != null) {
                    val count = data.clipData!!.itemCount
                    var i = 0
                    while (i < count) {
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        i++
                    }
                }
            } else if (data!!.data != null) {
                val imagePath = data.data!!.path
            }
            PICK_FROM_CAMERA -> if (resultCode == RESULT_OK) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, PICK_FROM_CAMERA)
            }
        }
    }

    companion object {
        private const val SELECT_PICTURES = 0
        private const val PICK_FROM_CAMERA = 1
    }
}