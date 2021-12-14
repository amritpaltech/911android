package com.soft.credit911.ui.signature

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.util.Base64
import android.util.Log
import com.github.gcacace.signaturepad.views.SignaturePad
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.soft.credit911.R
import com.soft.credit911.datamodel.data_docs
import com.soundcloud.android.crop.Crop
import kotlinx.android.synthetic.main.activity_signature.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import java.io.*


class SignatureActivity : SubBaseActivity() {

    var docData: data_docs.DocData? = null
    override fun getLayoutID(): Int {
        return R.layout.activity_signature
    }

    override fun onViewCreated() {
        toolbarTitle.text = "Place your Signature"
        txtHtml.setMovementMethod(ScrollingMovementMethod())
        docData = intent.getSerializableExtra("docData") as data_docs.DocData
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                txtHtml.setText(Html.fromHtml(docData?.agreement, Html.FROM_HTML_MODE_COMPACT));
            } else {
                txtHtml.setText(Html.fromHtml(docData?.agreement));
            }
        } catch (e: Exception) {
        }

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
                permissionsStorage,
                null /*rationale*/,
                null /*options*/,
                object : PermissionHandler() {
                    override fun onGranted() {
                        val photo = File(
                            getAlbumStorageDir("SignaturePad"),
                            String.format("Signature_%d.jpg", System.currentTimeMillis())
                        )
                        val signatureBitmap: Bitmap = signature_pad!!.signatureBitmap
                        if (addJpgSignatureToGallery(signatureBitmap,photo)) {
//                            CropImage.activity()
//                                .setGuidelines(CropImageView.Guidelines.ON)
//                                .start(this@SignatureActivity);



                          /*  val byteArrayOutputStream = ByteArrayOutputStream()
                            signatureBitmap.compress(
                                Bitmap.CompressFormat.PNG,
                                100,
                                byteArrayOutputStream
                            )
                            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                                                        }
                            val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
                            var ss = signatureObject()
                            ss.signatureStr = encoded
                            ss.docObj = docData

                            addJpgSignatureToGallery(signatureBitmap)
                            EventBus.getDefault().post(ss)
                            finish()*/
                        if(!signature_pad.isEmpty && photo.isFile) {
                            val destination = Uri.fromFile(File(cacheDir, "cropped"))
                            Crop.of(Uri.fromFile(photo), destination).asSquare().start(this@SignatureActivity)
                        }


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
//
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
//
    fun addJpgSignatureToGallery(signature: Bitmap, photo:File): Boolean {
        var result = false
        try {

            saveBitmapToJPG(signature, photo)
            scanMediaFile(photo)
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
//
     fun scanMediaFile(photo: File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val contentUri = Uri.fromFile(photo)
        mediaScanIntent.data = contentUri
        this@SignatureActivity.sendBroadcast(mediaScanIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, result: Intent?) {
        super.onActivityResult(requestCode, resultCode, result)
        if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            val imageUri: Uri? = Crop.getOutput(result)
//            if (photo2.isFile) {
                val imageStream = imageUri?.let { contentResolver.openInputStream(it) }
                val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
                val encodedImage: String = encodeImage(selectedImage).toString()
                var ss = signatureObject()
                ss.signatureStr = encodedImage
                ss.docObj = docData
            EventBus.getDefault().post(ss)
            finish()
//            }
        }
    }

    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}

class signatureObject{
    var signatureStr:String?=null
    var docObj:data_docs.DocData?=null
}