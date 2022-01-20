package com.soft.credit911.ui.Login

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.ing.quiz.shared_prefrences.Prefs
import com.ing.quiz.shared_prefrences.SharedPreferencesName
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.soft.credit911.R
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.adaptor.ViewPagerAdapter
import com.soft.credit911.ui.ForgotPassword.ForgetPasswordActivity
import com.soft.credit911.ui.OTPVerification.LoginVerificationActivity
import com.soft.credit911.ui.dashboard.LandingActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.String
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest


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

        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(this.resources.getColor(R.color.blue))
        }
    }

    private fun initView() {
        tv_Login.setOnClickListener { v: View? ->
            if (validate()) {
                mViewModel.signIn(
                    etUserEmail.text.toString().trim { it <= ' ' },
                    etUserPassword.text.toString().trim { it <= ' ' },
                    Prefs.with(this@LoginActivity).
                    getString(SharedPreferencesName.DEVICETOKEN,""))
                /*Permissions.check(
                    this *//*context*//*,
                    permissionsMessage,
                    null *//*rationale*//*,
                    null *//*options*//*,
                    object : PermissionHandler() {
                        override fun onGranted() {
                            mViewModel.signIn(
                                etUserEmail.text.toString().trim { it <= ' ' },
                                etUserPassword.text.toString().trim { it <= ' ' },
                            Prefs.with(this@LoginActivity).
                            getString(SharedPreferencesName.DEVICETOKEN,""))
                        }

                        override fun onDenied(
                            context: Context?,
                            deniedPermissions: ArrayList<kotlin.String>?
                        ) {
                            super.onDenied(context, deniedPermissions)
                            mViewModel.signIn(
                                etUserEmail.text.toString().trim { it <= ' ' },
                                etUserPassword.text.toString().trim { it <= ' ' },
                                Prefs.with(this@LoginActivity).
                                getString(SharedPreferencesName.DEVICETOKEN,""))
                        }
                    })*/

            }
        }
        tv_Forget_your_password.setOnClickListener { v: View? ->
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
//        val adapter = ViewPagerAdapter(this)
//        view_pager.adapter = adapter
//        view_pager.currentItem = 0
//        tutorialSliderTab.setupWithViewPager(view_pager)
    }

    private fun validate(): Boolean {
        if (etUserEmail.text.toString().length<=0) {
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
        if (etUserPassword.text.toString().length<=0) {
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
                        loginResponse.data?.id
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