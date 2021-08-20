package com.soft.credit911.ui.OTPVerification

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ing.quiz.ui.base_classes.BaseActivity
import com.intsab.otpfetcher.OtpFetcher
import com.intsab.otpfetcher.listeners.OtpListener
import com.intsab.otpfetcher.models.MessageItem
import com.soft.credit911.R
import com.soft.credit911.Utils.AppConstants
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.ui.dashboard.LandingActivity
import kotlinx.android.synthetic.main.activity_login_verification.*
import kotlinx.android.synthetic.main.toolbar.*
import java.lang.String


class LoginVerificationActivity : BaseActivity() {
    private var viewModel: OtpViewModel? = null

    var appPreference: AppPreference? = null
    override fun getLayoutID(): Int {
       return R.layout.activity_login_verification
    }

    override fun onViewCreated() {
        viewModel= OtpViewModel()
        attchObserver()
        appPreference = AppPreference(this)
        initView()

        OtpFetcher.getInstance().verifyOtpByMatchingString(this, "code", 25000*60, object :
            OtpListener {
            override fun onReceived(messageItem: MessageItem) {
                Toast.makeText(applicationContext, "" + messageItem.message, Toast.LENGTH_SHORT).show()
                etOtpNum1.setText("X")
                etOtpNum2.setText("X")
                etOtpNum3.setText("X")
                etOtpNum4.setText("X")
                etOtpNum5.setText("X")
                etOtpNum6.setText("X")
                try {
                    viewModel?.verifyOtp(messageItem.message.split(":")[1].trim())
                }catch (e:Exception){}
            }

            override fun onTimeOut() {


            }
        })
    }

    private fun initView() {

        toolbarTitle.text = "Login"
        navigationIcon.setOnClickListener {
                v: View? -> onBackPressed()
        }
        viewModel?.getOTPVerification()
        displayTextTv.text = resources.getString(R.string.verification_number) + " " + appPreference!!.userObject.data!!.phoneNumber
        etOtpNum1.addTextChangedListener(GenericTextWatcher(etOtpNum1))
        etOtpNum2.addTextChangedListener(GenericTextWatcher(etOtpNum2))
        etOtpNum3.addTextChangedListener(GenericTextWatcher(etOtpNum3))
        etOtpNum4.addTextChangedListener(GenericTextWatcher(etOtpNum4))
        etOtpNum5.addTextChangedListener(GenericTextWatcher(etOtpNum5))
        etOtpNum6.addTextChangedListener(GenericTextWatcher(etOtpNum6))
        resendOTPTv.setOnClickListener {
                v: View? -> viewModel?.getOTPVerification()
        }
        /*toolbarBinding.tvToolbarLogin.setVisibility(View.VISIBLE);*/
        tvVerify.setOnClickListener { v: View? ->
            if (isValid) {
                val otp = etOtpNum1.text.toString().trim { it <= ' ' } +
                        etOtpNum2.text.toString().trim { it <= ' ' } +
                        etOtpNum3.text.toString().trim { it <= ' ' } +
                        etOtpNum4.text.toString().trim { it <= ' ' } +
                        etOtpNum5.text.toString().trim { it <= ' ' } +
                        etOtpNum6.text.toString().trim { it <= ' ' }
                viewModel!!.verifyOtp(otp)
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

    fun attchObserver(){
        viewModel?.isLoading?.observe(this,androidx.lifecycle.Observer {

            if(it){
                showProgress()
            }else{
                hideProgress()
            }
        })

        viewModel?.getOtpResponse?.observe(this, Observer {generateOTPResponse->
            Toast.makeText(this, generateOTPResponse.message, Toast.LENGTH_SHORT).show()
        })
        viewModel?.apiError?.observe(this, androidx.lifecycle.Observer {
            CommonUtils.showdialog(it, this, false)
        })
        viewModel?.otpVeryResponse?.observe(this, Observer {generateOTPResponse->
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
                CommonUtils.showdialog(generateOTPResponse.message, this, false)
            }
        })

    }
}