package com.soft.credit911.ui.signature

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.Html
import android.util.Base64
import android.util.Log
import androidx.core.app.ActivityCompat
import com.github.gcacace.signaturepad.views.SignaturePad
import com.ing.quiz.ui.base_classes.BaseActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.soft.credit911.R
import com.soft.credit911.datamodel.data_docs
import kotlinx.android.synthetic.main.activity_signature.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import java.io.*


class SignatureActivity : BaseActivity() {

   var  docData: data_docs.DocData?=null
    override fun getLayoutID(): Int {
       return R.layout.activity_signature
    }

    override fun onViewCreated() {
        toolbarTitle.text = "Place your Signature"
        docData=intent.getSerializableExtra("docData") as data_docs.DocData
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtHtml.setText(Html.fromHtml(docData?.agreement, Html.FROM_HTML_MODE_COMPACT));
            } else {
                        txtHtml.setText(Html.fromHtml(docData?.agreement));
            }
        }catch (e:Exception){}
      
        signature_pad!!.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() {
               // Toast.makeText(this@SignatureActivity, "OnStartSigning", Toast.LENGTH_SHORT).show()
            }

            override fun onSigned() {
                mSaveButton!!.isEnabled = true
                mClearButton!!.isEnabled = true
            }

            override fun onClear() {
                mSaveButton!!.isEnabled = false
                mClearButton!!.isEnabled = false
            }
        })

        mClearButton!!.setOnClickListener { signature_pad!!.clear() }
        mSaveButton!!.setOnClickListener {


            Permissions.check(
                this /*context*/,
                permissionsMessage,
                null /*rationale*/,
                null /*options*/,
                object : PermissionHandler() {
                    override fun onGranted() {
                        val signatureBitmap:Bitmap = signature_pad!!.signatureBitmap
                        if (addJpgSignatureToGallery(signatureBitmap)) {
                            val byteArrayOutputStream = ByteArrayOutputStream()
                            signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                            val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
                            var ss=signatureObject()
                            ss.signatureStr=encoded
                            ss.docObj=docData
                            EventBus.getDefault().post(ss)
                            finish()
                        }
                    }

                    override fun onDenied(
                        context: Context?,
                        deniedPermissions: ArrayList<String?>?
                    ) {
                        // permission denied, block the feature.
                    }
                })

        }
    }


    fun getAlbumStorageDir(albumName: String?): File {
        // Get the directory for the user's public pictures directory.
        val file = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ), albumName
        )
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created")
        }
        return file
    }

    @Throws(IOException::class)
    fun saveBitmapToJPG(bitmap: Bitmap, photo: File?) {
        val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        val stream: OutputStream = FileOutputStream(photo)
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        stream.close()
    }

    fun addJpgSignatureToGallery(signature: Bitmap): Boolean {
        var result = false
        try {
            val photo = File(
                getAlbumStorageDir("SignaturePad"),
                String.format("Signature_%d.jpg", System.currentTimeMillis())
            )
            saveBitmapToJPG(signature, photo)
            scanMediaFile(photo)
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    private fun scanMediaFile(photo: File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val contentUri = Uri.fromFile(photo)
        mediaScanIntent.data = contentUri
        this@SignatureActivity.sendBroadcast(mediaScanIntent)
    }

    fun addSvgSignatureToGallery(signatureSvg: String?): Boolean {
        var result = false
        try {
            val svgFile = File(
                getAlbumStorageDir("SignaturePad"),
                String.format("Signature_%d.svg", System.currentTimeMillis())
            )
            val stream: OutputStream = FileOutputStream(svgFile)
            val writer = OutputStreamWriter(stream)
            writer.write(signatureSvg)
            writer.close()
            stream.flush()
            stream.close()
            scanMediaFile(svgFile)
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    companion object {
        private const val REQUEST_EXTERNAL_STORAGE = 1
        private val PERMISSIONS_STORAGE = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        fun verifyStoragePermissions(activity: Activity?) {
            val permission = ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )
            }
        }
    }
}

class signatureObject{
    var signatureStr:String?=null
    var docObj:data_docs.DocData?=null
}