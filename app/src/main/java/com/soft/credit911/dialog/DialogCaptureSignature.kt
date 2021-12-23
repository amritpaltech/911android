package com.soft.credit911.dialog

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import com.github.gcacace.signaturepad.views.SignaturePad
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.soft.credit911.R
import com.soundcloud.android.crop.Crop
import kotlinx.android.synthetic.main.activity_signature.*
import kotlinx.android.synthetic.main.dialog_add_signature.*
import kotlinx.android.synthetic.main.dialog_add_signature.mClearButton
import kotlinx.android.synthetic.main.dialog_add_signature.mSaveButton
import kotlinx.android.synthetic.main.dialog_add_signature.signature_pad
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class DialogCaptureSignature(val  onClick: (op:Int) -> Unit) : BaseFragmentDialog() {


    var data:String?=null
    val permissionsStorage = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    override fun onCreated() {
        onClickEvents()
        setData()
    }

    fun setPopupInfo(data:String){
        this.data=data
    }
    override fun getLayoutID(): Int {
        return R.layout.dialog_add_signature
    }

    fun onClickEvents()
    {

        closeBtn.setOnClickListener {
            dismiss()
        }

    }

    fun setData()
    {
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
                activity /*context*/,
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
                            if(!signature_pad.isEmpty && photo.isFile) {
                                val destination = Uri.fromFile(File(activity?.cacheDir, "cropped"))
                                Crop.of(Uri.fromFile(photo), destination).asSquare().start(activity)
                                dismiss()
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
        activity?.sendBroadcast(mediaScanIntent)
    }
}