package com.soft.credit911.Utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class CommonUtils {
    private var dialog_progress: Dialog? = null

    /*calling  showProgress*/
    fun showProgress(context: Context) {
        try {
            if (dialog_progress == null) {
                initProgress(context)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            if (!dialog_progress!!.isShowing && !(context as Activity).isFinishing) {
                dialog_progress!!.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /*calling  initProgress*/
    private fun initProgress(context: Context) {
        try {
            dialog_progress = Dialog(context, R.style.CustomDialogTime)
            dialog_progress!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog_progress!!.window!!
                .setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            context,
                            android.R.color.transparent
                        )
                    )
                )
            dialog_progress!!.setCancelable(false)
            dialog_progress!!.setContentView(R.layout.dialog_progress_overlay)
            //            dialog_progress.getWindow().getDecorView().setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /*calling  hideProgress*/
    fun hideProgress() {
        try {
            if (dialog_progress != null) {
                if (dialog_progress!!.isShowing) dialog_progress!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private val TAG = CommonUtils::class.java.simpleName
        fun isValidEmail(target: CharSequence?): Boolean {
            return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }

        @JvmStatic
        fun showdialog(msg: String?, context: Context, closeActivity: Boolean) {
            try {
                val dialog = Dialog(context)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
                dialog.setCanceledOnTouchOutside(false)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(context.resources.getColor(R.color.transparent_background)))
                dialog.setContentView(R.layout.dialog_custom)
                dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
                val btnCloseDialog = dialog.findViewById<View>(R.id.btnCloseDialog) as TextView
                val tv_msg = dialog.findViewById<View>(R.id.tv_msg) as TextView
                tv_msg.text = msg
                btnCloseDialog.setOnClickListener {
                    if (dialog.isShowing) {
                        dialog.dismiss()
                        if (closeActivity) (context as Activity).onBackPressed()
                    }
                }
                dialog.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun handleServerResponses(error :com.jakewharton.retrofit2.adapter.rxjava2.HttpException):String{
            var body=(error as com.jakewharton.retrofit2.adapter.rxjava2.HttpException).response().errorBody()?.string()
            if (body?.let { checkIfObjisJson(it) || checkIfJSONArray(it) } == true){
                var obj= JSONObject(body)
                return parseData(JSONObject(obj.getString("message")))
            }else{
                return body.toString()
            }
        }
        fun parseData(data:JSONObject):String{
            val iter: Iterator<String> = data.keys()
            var str=""
            while (iter.hasNext()) {
                val key = iter.next()
                try {
                    val value: Any = data.get(key)
                    if(checkIfObjisJson(value.toString())){
                        str=str+" "+parseData(JSONObject(value.toString()))
                    }
                    else if(checkIfJSONArray(value.toString())){
                        val arr= JSONArray(value.toString())
                        for (i in 0 until arr.length()) {
                            str=str+" "+arr.get(i)
                        }

                    }
                    else{
                        str=str+" "+value.toString()
                    }

                } catch (e: JSONException) {
                    // Something went wrong!
                }
            }
            return str

        }

        fun checkIfJSONArray(test:String):Boolean{
            try {
                JSONArray(test)
            } catch (ex1: JSONException) {
                return false
            }
            return true
        }

        fun checkIfObjisJson(test:String):Boolean{
            try {
                JSONObject(test)
            } catch (ex: JSONException) {
               return false
            }
            return true
        }
    }




}