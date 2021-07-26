package com.soft.credit911.Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.ForgotPassword.ForgetPasswordActivity
import com.soft.credit911.Landing.LandingActivity
import com.soft.credit911.OTPVerification.LoginVerificationActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.AppConstants
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.adaptor.ViewPagerAdapter
import com.soft.credit911.databinding.ActivityMainBinding
import com.soft.credit911.datamodel.LoginResponse
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.String
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : SubBaseActivity() {
   
    var mViewModel= LoginViewModel()
    var appPreference: AppPreference? = null
    

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun onViewCreated() {
        appPreference = AppPreference(this)
        initView()
        attachObserver()
    }

    private fun initView() {
        tv_Login.setOnClickListener { v: View? ->
            if (validate()) {
                mViewModel.signIn(
                    etUserEmail.text.toString().trim { it <= ' ' },
                    etUserPassword.text.toString().trim { it <= ' ' })
            }
        }
        tv_Forget_your_password.setOnClickListener { v: View? ->
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        val adapter = ViewPagerAdapter(this)
        view_pager.adapter = adapter
        view_pager.currentItem = 0
        tutorialSliderTab.setupWithViewPager(view_pager)
    }

    private fun validate(): Boolean {
        if (etUserEmail.text.toString().trim { it <= ' ' } == "") {
            etUserEmail.error = resources.getString(R.string.email_error)
            etUserEmail.requestFocus()
            return false
        }
        if (!CommonUtils.isValidEmail(
                etUserEmail.text.toString().trim { it <= ' ' })
        ) {
            etUserEmail.error = resources.getString(R.string.invalid_email_error)
            etUserEmail.requestFocus()
            return false
        }
        if (etUserPassword.text.toString().trim { it <= ' ' } == "") {
            etUserPassword.error = resources.getString(R.string.your_password)
            etUserPassword.requestFocus()
            return false
        }
        return true
    }

    fun attachObserver() {

        mViewModel.isLoading.observe(this,androidx.lifecycle.Observer {

            if(it){
                showProgress()
            }else{
                hideProgress()
            }
        })
        mViewModel.responseAppHomedata.observe(this, androidx.lifecycle.Observer { loginResponse->
            val dtStart = loginResponse.data!!.token2faExpiry
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val today = Calendar.getInstance()
            var expireDate: Date? = null
            try {
                expireDate = format.parse(dtStart)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            AppPreference(this).userObject = loginResponse
            if (expireDate!!.after(today.time) && appPreference!!.userLoggedIn.contains(
                    String.valueOf(
                        loginResponse.data.id
                    )
                )
            ) {
                appPreference!!.isLogin = true
                val intent = Intent(this, LandingActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginVerificationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }

        })
        mViewModel.apiError.observe(this, androidx.lifecycle.Observer {
            CommonUtils.showdialog(it, this, false)
        })
    }
}