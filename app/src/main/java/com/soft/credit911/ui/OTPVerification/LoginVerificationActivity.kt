package com.soft.credit911.ui.OTPVerification

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ing.quiz.ui.base_classes.BaseActivity
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
import kotlinx.android.synthetic.main.activity_login_verification.*
import kotlinx.android.synthetic.main.toolbar.*
import java.lang.String

class LoginVerificationActivity : BaseActivity(), OTPVerificationView {
    private var OTPVerificationPresenter: OTPVerificationPresenter? = null

    var appPreference: AppPreference? = null
    override fun getLayoutID(): Int {
       return R.layout.activity_login_verification
    }

    override fun onViewCreated() {
        OTPVerificationPresenter = OTPVerificationPresenter(this, this)
        appPreference = AppPreference(this)
        initView()
    }

    private fun initView() {

        toolbarTitle.text = "Login"
        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        OTPVerificationPresenter!!.getOTPVerification()
        displayTextTv.text =
            resources.getString(R.string.verification_number) + " " + appPreference!!.userObject.data!!.phoneNumber
        etOtpNum1.addTextChangedListener(GenericTextWatcher(etOtpNum1))
        etOtpNum2.addTextChangedListener(GenericTextWatcher(etOtpNum2))
        etOtpNum3.addTextChangedListener(GenericTextWatcher(etOtpNum3))
        etOtpNum4.addTextChangedListener(GenericTextWatcher(etOtpNum4))
        etOtpNum5.addTextChangedListener(GenericTextWatcher(etOtpNum5))
        etOtpNum6.addTextChangedListener(GenericTextWatcher(etOtpNum6))
        resendOTPTv.setOnClickListener { v: View? -> OTPVerificationPresenter!!.getOTPVerification() }
        /*toolbarBinding.tvToolbarLogin.setVisibility(View.VISIBLE);*/
        tvVerify.setOnClickListener { v: View? ->
            if (isValid) {
                val otp = etOtpNum1.text.toString().trim { it <= ' ' } +
                        etOtpNum2.text.toString().trim { it <= ' ' } +
                        etOtpNum3.text.toString().trim { it <= ' ' } +
                        etOtpNum4.text.toString().trim { it <= ' ' } +
                        etOtpNum5.text.toString().trim { it <= ' ' } +
                        etOtpNum6.text.toString().trim { it <= ' ' }
                OTPVerificationPresenter!!.verifiyOTP(otp)
            }
        }
    }

    private val isValid: Boolean
        private get() {
            if (etOtpNum1.text.toString().trim { it <= ' ' } == "") {
                etOtpNum1.error = resources.getString(R.string.otp_error)
                etOtpNum1.requestFocus()
                return false
            }
            if (etOtpNum2.text.toString().trim { it <= ' ' } == "") {
                etOtpNum2.error = resources.getString(R.string.otp_error)
                etOtpNum2.requestFocus()
                return false
            }
            if (etOtpNum3.text.toString().trim { it <= ' ' } == "") {
                etOtpNum3.error = resources.getString(R.string.otp_error)
                etOtpNum3.requestFocus()
                return false
            }
            if (etOtpNum4.text.toString().trim { it <= ' ' } == "") {
                etOtpNum4.error = resources.getString(R.string.otp_error)
                etOtpNum4.requestFocus()
                return false
            }
            if (etOtpNum5.text.toString().trim { it <= ' ' } == "") {
                etOtpNum5.error = resources.getString(R.string.otp_error)
                etOtpNum5.requestFocus()
                return false
            }
            if (etOtpNum6.text.toString().trim { it <= ' ' } == "") {
                etOtpNum6.error = resources.getString(R.string.otp_error)
                etOtpNum6.requestFocus()
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
                R.id.etOtpNum1 -> if (text.length == 1) etOtpNum2.requestFocus()
                R.id.etOtpNum2 -> if (text.length == 1) etOtpNum3.requestFocus() else if (text.length == 0) etOtpNum1.requestFocus()
                R.id.etOtpNum3 -> if (text.length == 1) etOtpNum4.requestFocus() else if (text.length == 0) etOtpNum2.requestFocus()
                R.id.etOtpNum4 -> if (text.length == 1) etOtpNum5.requestFocus() else if (text.length == 0) etOtpNum3.requestFocus()
                R.id.etOtpNum5 -> if (text.length == 1) etOtpNum6.requestFocus() else if (text.length == 0) etOtpNum4.requestFocus()
                R.id.etOtpNum6 -> if (text.length == 0) etOtpNum5.requestFocus()
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    }
}