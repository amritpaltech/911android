package com.soft.credit911.ui.Changepassword

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.databinding.ActivityChangePassWordBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.datamodel.ChangePasswordResponse
import com.soft.credit911.ui.Changepassword.mvp.ChangePasswordPresenter
import com.soft.credit911.ui.Changepassword.mvp.ChangePasswordView

class ChangePasswordActivity : AppCompatActivity(), ChangePasswordView {
    private var layoutBinding: ActivityChangePassWordBinding? = null
    private var toolbarBinding: ToolbarBinding? = null
    var changePasswordPresenter: ChangePasswordPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityChangePassWordBinding.inflate(
            layoutInflater
        )
        val view = layoutBinding!!.root
        setContentView(view)
        changePasswordPresenter = ChangePasswordPresenter(this, this)
        toolbarBinding = layoutBinding!!.toolbarLayout
        toolbarBinding!!.toolbarTitle.text = "Change Password"
        toolbarBinding!!.navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        initView()
    }

    private fun initView() {
        layoutBinding!!.tvSave.setOnClickListener { v: View? ->
            if (isValid) {
                changePasswordPresenter!!.changePassword(
                    layoutBinding!!.etCurrentPassword.text.toString().trim { it <= ' ' },
                    layoutBinding!!.etNewPassword.text.toString().trim { it <= ' ' },
                    layoutBinding!!.etConfirmPassword.text.toString().trim { it <= ' ' })
            }
        }
    }

    /*    if (!layoutBinding.etNewPassword.equals(layoutBinding.etConfirmPassword)) {
            layoutBinding.etConfirmPassword.setError(getResources().getString(R.string.password_not_matching));
            layoutBinding.etConfirmPassword.requestFocus();
            return false;
        }*/
    private val isValid: Boolean
        private get() {
            if (layoutBinding!!.etConfirmPassword.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etConfirmPassword.error =
                    resources.getString(R.string.currentPassword_error)
                layoutBinding!!.etConfirmPassword.requestFocus()
                return false
            }
            if (layoutBinding!!.etNewPassword.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etNewPassword.error =
                    resources.getString(R.string.newPassword_error)
                layoutBinding!!.etNewPassword.requestFocus()
                return false
            }
            if (layoutBinding!!.etConfirmPassword.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etConfirmPassword.error =
                    resources.getString(R.string.new_Confirm_Password_error)
                layoutBinding!!.etConfirmPassword.requestFocus()
                return false
            }
            /*    if (!layoutBinding.etNewPassword.equals(layoutBinding.etConfirmPassword)) {
            layoutBinding.etConfirmPassword.setError(getResources().getString(R.string.password_not_matching));
            layoutBinding.etConfirmPassword.requestFocus();
            return false;
        }*/return true
        }

    override fun ChangePasswordResponse(changePasswordResponse: ChangePasswordResponse) {
        CommonUtils.showdialog(changePasswordResponse.message, this, false)
    }
}