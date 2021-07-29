package com.soft.credit911.ui.dashboard.UserProfile.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.R
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.loadImg
import com.soft.credit911.databinding.FragmentUserProfileBinding
import com.soft.credit911.datamodel.UpdateProfileResponse
import com.soft.credit911.ui.Changepassword.ChangePasswordActivity
import com.soft.credit911.ui.Chat.Activity.ChatActivity
import com.soft.credit911.ui.Login.LoginActivity
import com.soft.credit911.ui.MyProfile.Activity.MyProfileActivity
import com.soft.credit911.ui.casemanagement.CaseManagementActivity
import com.soft.credit911.ui.dashboard.UserProfile.mvp.UpdateProfilePresenter
import com.soft.credit911.ui.dashboard.UserProfile.mvp.UpdateProfileView
import com.soft.credit911.ui.documnet.DocumentActivity
import com.soft.credit911.ui.notifications.NotificationActivity
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment : BaseFragment(), UpdateProfileView {
  
    

    var updateProfilePresenter: UpdateProfilePresenter? = null
    override fun getLayoutID(): Int {
        return R.layout.fragment_user_profile
    }

    override fun onViewCreated() {
        setProfileData()
    }

    fun setProfileData(){
        userName.text= AppPreference(activity).getUserObject().data?.firstName+" "+
                AppPreference(activity).getUserObject().data?.lastName
        mobile.text=AppPreference(activity).getUserObject().data?.phoneNumber
        emailId.text=AppPreference(activity).getUserObject().data?.email
        iv_user_img.loadImg(AppPreference(activity).getUserObject().data?.userAvatar)

        tv_My_Profile.setOnClickListener { v: View? ->
            val intent = Intent(activity, MyProfileActivity::class.java)
            activity?.startActivity(intent)
        }
       tv_Change_password.setOnClickListener { v: View? ->
            val intent = Intent(activity, ChangePasswordActivity::class.java)
            activity?.startActivity(intent)
        }
       tv_Chat.setOnClickListener { v: View? ->
            val intent = Intent(activity, ChatActivity::class.java)
            activity?.startActivity(intent)
        }
        tv_Document.setOnClickListener { v: View? ->
            val intent = Intent(activity, DocumentActivity::class.java)
            activity?.startActivity(intent)
        }
       tv_notification.setOnClickListener { v: View? ->
            val intent = Intent(activity, NotificationActivity::class.java)
            activity?.startActivity(intent)
        }

        tv_Case_Management.setOnClickListener { v: View? ->
            val intent = Intent(activity, CaseManagementActivity::class.java)
            activity?.startActivity(intent)
        }
        logoutTv.setOnClickListener { v: View? ->
            AppPreference(activity).isLogin = false
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            activity?.startActivity(intent)
        }
    }

    private fun initView() {
        updateProfilePresenter!!.updateProfileAvatar()
    }

    override fun UpdateProfileResponse(updateProfileResponse: UpdateProfileResponse) {
        /*CommonUtils.showdialog(updateProfileResponse.getMessage(), getContext(), false);*/
    }
    
}