//package com.soft.credit911.ui.Changepassword
//
//import android.view.View
//import android.widget.Toast
//import androidx.lifecycle.Observer
//import com.ing.quiz.ui.base_classes.SubBaseActivity
//import com.soft.credit911.R
//import com.soft.credit911.Utils.AppPreference
//import com.soft.credit911.Utils.CommonUtils
//import com.soft.credit911.datamodel.ChangePasswordResponse
//import com.soft.credit911.fcm.notificationObject
//import kotlinx.android.synthetic.main.activity_scan_doc.*
//import kotlinx.android.synthetic.main.toolbar.*
//
//class ScanDocActivity : SubBaseActivity() {
//
//    var mViewModel: ScanDocViewModel? = ScanDocViewModel()
//
//
//    override fun getLayoutID(): Int {
//        return R.layout.activity_scan_doc
//    }
//
//    override fun onViewCreated() {
//        toolbarTitle.text = "Scan credit Credentials"
//        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
//        initView()
//        attachObserver()
//    }
//
//    private fun initView() {
//        tv_Save.setOnClickListener { v: View? ->
//            if (isValid) {
//                mViewModel?.changePassword(
//                    etNewPassword.text.toString().trim { it <= ' ' },
//                    etConfirmPassword.text.toString().trim { it <= ' ' })
//            }
//        }
//        etNewPassword.setText(AppPreference(this).getUserObject().data?.scancredit_username)
//        etConfirmPassword.setText(AppPreference(this).getUserObject().data?.scancredit_password)
//    }
//
//
//    private val isValid: Boolean
//        private get() {
//            if (etNewPassword.text.toString().length<4) {
//                etNewPassword.error ="Enter User name"
//                etNewPassword.requestFocus()
//                return false
//            }
//            if (etConfirmPassword.text.toString().length<8) {
//                etConfirmPassword.error ="Enter valid password"
//                etConfirmPassword.requestFocus()
//                return false
//            }
//
//            return true
//        }
//
//
//    fun attachObserver() {
//        mViewModel?.apiError?.observe(this, androidx.lifecycle.Observer {
//            CommonUtils.showdialog(it, this, false)
//        })
//
//
//        mViewModel?.isLoading?.observe(this, androidx.lifecycle.Observer {
//
//            if (it) {
//                showProgress()
//            } else {
//                hideProgress()
//            }
//        })
//
//        mViewModel?.responsePasswordChange?.observe(this, Observer {
//            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//            finish()
//        })
//    }
//}