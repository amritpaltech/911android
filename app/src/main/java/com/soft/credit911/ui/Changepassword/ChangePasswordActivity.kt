package com.soft.credit911.ui.Changepassword

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.datamodel.ChangePasswordResponse
import kotlinx.android.synthetic.main.activity_change_pass_word.*
import kotlinx.android.synthetic.main.toolbar.*

class ChangePasswordActivity : SubBaseActivity() {

    var mViewModel: ChangePasswordViewModel? = ChangePasswordViewModel()


    override fun getLayoutID(): Int {
        return R.layout.activity_change_pass_word
    }

    override fun onViewCreated() {
        toolbarTitle.text = "Change Password"
        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        initView()
        attachObserver()
    }

    private fun initView() {
        tv_Save.setOnClickListener { v: View? ->
            if (isValid) {
                mViewModel?.changePassword(
                    etCurrentPassword.text.toString().trim { it <= ' ' },
                    etNewPassword.text.toString().trim { it <= ' ' },
                    etConfirmPassword.text.toString().trim { it <= ' ' })
            }
        }
    }


    private val isValid: Boolean
        private get() {
            if (etCurrentPassword.text.toString().length<4) {
                etCurrentPassword.error =
                    resources.getString(R.string.currentPassword_error)
                etCurrentPassword.requestFocus()
                return false
            }
            if (etNewPassword.text.toString().length<8) {
                etNewPassword.error =
                    resources.getString(R.string.newPassword_error)
                etNewPassword.requestFocus()
                return false
            }
            if (!etConfirmPassword.text.toString().equals(etNewPassword.text.toString())) {
                etConfirmPassword.error =
                    resources.getString(R.string.new_Confirm_Password_error)
                etConfirmPassword.requestFocus()
                return false
            }
            return true
        }


    fun attachObserver() {
        mViewModel?.apiError?.observe(this, androidx.lifecycle.Observer {
            CommonUtils.showdialog(it, this, false)
        })


        mViewModel?.isLoading?.observe(this, androidx.lifecycle.Observer {

            if (it) {
                showProgress()
            } else {
                hideProgress()
            }
        })

        mViewModel?.responsePasswordChange?.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            finish()
        })
    }
}