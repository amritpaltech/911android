package com.soft.credit911.ui.signature

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.util.Log
import com.github.gcacace.signaturepad.views.SignaturePad
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.soft.credit911.R
import com.soft.credit911.datamodel.data_docs
import com.soundcloud.android.crop.Crop
import kotlinx.android.synthetic.main.activity_signature2.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import java.io.*


class SignatureProfileActivity : SubBaseActivity() {
    override fun getLayoutID(): Int {
        return R.layout.activity_signature2
    }

    override fun onViewCreated() {
        navigationIcon.setOnClickListener {
            finish()
        }
        toolbarTitle.text = "Place your Signature"
        signature_pad!!.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() {
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
                        val signatureBitmap: Bitmap = signature_pad!!.signatureBitmap
                        val encodedImage: String = encodeImage(signatureBitmap).toString()
                        var ss = signatureObject()
                        ss.signatureStr = encodedImage
                        EventBus.getDefault().post(ss)
                        finish()
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



    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}

