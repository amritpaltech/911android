package com.soft.credit911.ui.ForgotPassword

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.soft.credit911.Utils.AppConstants
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.databinding.ActivityForgetPasswordBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.datamodel.ForgotPasswordResponse
import com.soft.credit911.ui.ForgotPassword.mvp.ForgotPasswordPresenter
import com.soft.credit911.ui.ForgotPassword.mvp.ForgotPasswordView

class ForgetPasswordActivity : AppCompatActivity(), ForgotPasswordView {
    private var layoutBinding: ActivityForgetPasswordBinding? = null
    private var toolbarBinding: ToolbarBinding? = null
    private var forgotPasswordPresenter: ForgotPasswordPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityForgetPasswordBinding.inflate(
            layoutInflater
        )
        toolbarBinding = layoutBinding!!.toolbarLayout
        toolbarBinding!!.toolbarTitle.text = "Forgot Password"
        toolbarBinding!!.navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        val view = layoutBinding!!.root
        setContentView(view)
        forgotPasswordPresenter = ForgotPasswordPresenter(this, this)
        layoutBinding!!.tvResetPassword.setOnClickListener { v: View? ->
            if (validate()) forgotPasswordPresenter!!.forgotPassword(
                layoutBinding!!.etUserEmail.text.toString().trim { it <= ' ' })
        }
    }

    private fun validate(): Boolean {
        if (layoutBinding!!.etUserEmail.text.toString().trim { it <= ' ' } == "") {
            layoutBinding!!.etUserEmail.error = "Enter Your Email Id"
            layoutBinding!!.etUserEmail.requestFocus()
            return false
        }
        if (!CommonUtils.isValidEmail(
                layoutBinding!!.etUserEmail.text.toString().trim { it <= ' ' })
        ) {
            layoutBinding!!.etUserEmail.error = "Invalid Email Id"
            layoutBinding!!.etUserEmail.requestFocus()
            return false
        }
        return true
    }

    override fun forgotPasswordResponse(forgotPasswordResponse: ForgotPasswordResponse) {
        CommonUtils.showdialog(
            forgotPasswordResponse.message,
            this,
            forgotPasswordResponse.status == AppConstants.API_SUCCESS
        )
        //Toast.makeText(this, forgotPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }
}