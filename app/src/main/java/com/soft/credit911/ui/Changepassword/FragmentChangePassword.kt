package com.soft.credit911.ui.Changepassword

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ing.quiz.ui.base_classes.BaseFragment
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.datamodel.ChangePasswordResponse
import com.soft.credit911.fcm.notificationObject
import com.soft.credit911.ui.dashboard.LandingActivity
import kotlinx.android.synthetic.main.activity_change_pass_word.*
import kotlinx.android.synthetic.main.toolbar.*

class FragmentChangePassword : BaseFragment() {

    var mViewModel: ChangePasswordViewModel? = ChangePasswordViewModel()


    override fun getLayoutID(): Int {
        return R.layout.activity_change_pass_word
    }

    override fun onViewCreated() {
        toolbarTitle.text = "Change Password"
        navigationIcon.setOnClickListener { v: View? -> super.onBackPress() }
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
        if(arguments?.containsKey("pushData")==true){
            (activity as LandingActivity).pushDataMain = arguments?.getSerializable("pushData") as notificationObject
            (activity as LandingActivity).showPushDialog()
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
        mViewModel?.apiError?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            activity?.let { it1 -> CommonUtils.showdialog(it, it1, false) }
        })


        mViewModel?.isLoading?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            if (it) {
                showProgress()
            } else {
                hideProgress()
            }
        })

        mViewModel?.responsePasswordChange?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
            super.onBackPress()
        })
    }
}