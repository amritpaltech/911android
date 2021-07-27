package com.soft.credit911.ui.OTPVerification

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.AppConstants
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.databinding.ActivityLoginVerificationBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.datamodel.GenerateOTPResponse
import com.soft.credit911.ui.OTPVerification.mvp.OTPVerificationPresenter
import com.soft.credit911.ui.OTPVerification.mvp.OTPVerificationView
import com.soft.credit911.ui.dashboard.LandingActivity
import java.lang.String

class LoginVerificationActivity : AppCompatActivity(), OTPVerificationView {
    private var layoutBinding: ActivityLoginVerificationBinding? = null
    private var toolbarBinding: ToolbarBinding? = null
    private var OTPVerificationPresenter: OTPVerificationPresenter? = null
    var appPreference: AppPreference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityLoginVerificationBinding.inflate(
            layoutInflater
        )
        val view = layoutBinding!!.root
        setContentView(view)
        OTPVerificationPresenter = OTPVerificationPresenter(this, this)
        appPreference = AppPreference(this)
        initView()
    }

    private fun initView() {
        toolbarBinding = layoutBinding!!.toolbarLayout
        toolbarBinding!!.toolbarTitle.text = "Login"
        toolbarBinding!!.navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        OTPVerificationPresenter!!.getOTPVerification()
        layoutBinding!!.displayTextTv.text =
            resources.getString(R.string.verification_number) + " " + appPreference!!.userObject.data!!.phoneNumber
        layoutBinding!!.etOtpNum1.addTextChangedListener(GenericTextWatcher(layoutBinding!!.etOtpNum1))
        layoutBinding!!.etOtpNum2.addTextChangedListener(GenericTextWatcher(layoutBinding!!.etOtpNum2))
        layoutBinding!!.etOtpNum3.addTextChangedListener(GenericTextWatcher(layoutBinding!!.etOtpNum3))
        layoutBinding!!.etOtpNum4.addTextChangedListener(GenericTextWatcher(layoutBinding!!.etOtpNum4))
        layoutBinding!!.etOtpNum5.addTextChangedListener(GenericTextWatcher(layoutBinding!!.etOtpNum5))
        layoutBinding!!.etOtpNum6.addTextChangedListener(GenericTextWatcher(layoutBinding!!.etOtpNum6))
        layoutBinding!!.resendOTPTv.setOnClickListener { v: View? -> OTPVerificationPresenter!!.getOTPVerification() }
        /*toolbarBinding.tvToolbarLogin.setVisibility(View.VISIBLE);*/layoutBinding!!.tvVerify.setOnClickListener { v: View? ->
            if (isValid) {
                val otp = layoutBinding!!.etOtpNum1.text.toString().trim { it <= ' ' } +
                        layoutBinding!!.etOtpNum2.text.toString().trim { it <= ' ' } +
                        layoutBinding!!.etOtpNum3.text.toString().trim { it <= ' ' } +
                        layoutBinding!!.etOtpNum4.text.toString().trim { it <= ' ' } +
                        layoutBinding!!.etOtpNum5.text.toString().trim { it <= ' ' } +
                        layoutBinding!!.etOtpNum6.text.toString().trim { it <= ' ' }
                OTPVerificationPresenter!!.verifiyOTP(otp)
            }
        }
    }

    private val isValid: Boolean
        private get() {
            if (layoutBinding!!.etOtpNum1.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etOtpNum1.error = resources.getString(R.string.otp_error)
                layoutBinding!!.etOtpNum1.requestFocus()
                return false
            }
            if (layoutBinding!!.etOtpNum2.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etOtpNum2.error = resources.getString(R.string.otp_error)
                layoutBinding!!.etOtpNum2.requestFocus()
                return false
            }
            if (layoutBinding!!.etOtpNum3.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etOtpNum3.error = resources.getString(R.string.otp_error)
                layoutBinding!!.etOtpNum3.requestFocus()
                return false
            }
            if (layoutBinding!!.etOtpNum4.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etOtpNum4.error = resources.getString(R.string.otp_error)
                layoutBinding!!.etOtpNum4.requestFocus()
                return false
            }
            if (layoutBinding!!.etOtpNum5.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etOtpNum5.error = resources.getString(R.string.otp_error)
                layoutBinding!!.etOtpNum5.requestFocus()
                return false
            }
            if (layoutBinding!!.etOtpNum6.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etOtpNum6.error = resources.getString(R.string.otp_error)
                layoutBinding!!.etOtpNum6.requestFocus()
                return false
            }
            return true
        }

    override fun generateOTPResponse(generateOTPResponse: GenerateOTPResponse) {
        Toast.makeText(this, generateOTPResponse.message, Toast.LENGTH_SHORT).show()
    }

    override fun verifiyOTPResponse(generateOTPResponse: GenerateOTPResponse) {
        if (generateOTPResponse.status == AppConstants.API_SUCCESS) {
            appPreference!!.isLogin = true
            if (appPreference!!.userObjectString != "") appPreference!!.userLoggedIn =
                String.valueOf(
                    appPreference!!.userObject.data!!.id
                )
            Toast.makeText(this, generateOTPResponse.message, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LandingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        } else {
            //Toast.makeText(this, generateOTPResponse.getMessage(), Toast.LENGTH_SHORT).show();
            CommonUtils.showdialog(generateOTPResponse.message, this, false)
        }
    }

    internal inner class GenericTextWatcher(private val view: View) : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            // TODO Auto-generated method stub
            val text = editable.toString()
            when (view.id) {
                R.id.et_otpNum1 -> if (text.length == 1) layoutBinding!!.etOtpNum2.requestFocus()
                R.id.et_otpNum2 -> if (text.length == 1) layoutBinding!!.etOtpNum3.requestFocus() else if (text.length == 0) layoutBinding!!.etOtpNum1.requestFocus()
                R.id.et_otpNum3 -> if (text.length == 1) layoutBinding!!.etOtpNum4.requestFocus() else if (text.length == 0) layoutBinding!!.etOtpNum2.requestFocus()
                R.id.et_otpNum4 -> if (text.length == 1) layoutBinding!!.etOtpNum5.requestFocus() else if (text.length == 0) layoutBinding!!.etOtpNum3.requestFocus()
                R.id.et_otpNum5 -> if (text.length == 1) layoutBinding!!.etOtpNum6.requestFocus() else if (text.length == 0) layoutBinding!!.etOtpNum4.requestFocus()
                R.id.et_otpNum6 -> if (text.length == 0) layoutBinding!!.etOtpNum5.requestFocus()
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    }
}