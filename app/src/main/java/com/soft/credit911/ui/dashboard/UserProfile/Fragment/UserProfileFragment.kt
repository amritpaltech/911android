package com.soft.credit911.ui.dashboard.UserProfile.Fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.chuzi.utils.URIPathHelper
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ing.quiz.ui.base_classes.BaseActivity
import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.R
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.Utils.loadImg
import com.soft.credit911.Utils.loadProfileImage
import com.soft.credit911.datamodel.LoginResponse
import com.soft.credit911.ui.Changepassword.ChangePasswordActivity
import com.soft.credit911.ui.Chat.Activity.ChatActivity
import com.soft.credit911.ui.Login.LoginActivity
import com.soft.credit911.ui.MyProfile.Activity.MyProfileActivity
import com.soft.credit911.ui.casemanagement.CaseManagementActivity
import com.soft.credit911.ui.dashboard.LandingActivity
import com.soft.credit911.ui.documnet.DocumentActivity
import com.soft.credit911.ui.notifications.NotificationActivity
import kotlinx.android.synthetic.main.fragment_user_profile.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.*


class UserProfileFragment : BaseFragment() {
  
    

    var viewmodel: ProfileViewModel? = null
    override fun getLayoutID(): Int {
        return R.layout.fragment_user_profile
    }

    override fun onViewCreated() {
        setprofileInfo()
        EventBus.getDefault().register(this)
        setOnClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun setOnClick(){
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
          (activity as  LandingActivity).selectCaseScreen()
        }
        logoutTv.setOnClickListener { v: View? ->
            AppPreference(activity).isLogin = false
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            activity?.startActivity(intent)
        }

        upload.setOnClickListener {
               ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        viewmodel= ProfileViewModel()
        attachObserver()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uriPathHelper = URIPathHelper()
            val selectedVideoPath = data?.data?.let { uriPathHelper.getPath(activity as BaseActivity, it) }
            iv_user_img.loadProfileImage(data?.data.toString())
//            baseActivity?.showProgress()

              val bitmap = BitmapFactory.decodeFile(selectedVideoPath)
                var str=getEncoded64ImageStringFromBitmap(bitmap)
                str?.let { viewmodel?.uploadProfileScreen(it) }


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(activity, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {

        }
    }

    fun attachObserver(){
        viewmodel?.isLoading?.observe(viewLifecycleOwner, Observer {
            if(it){
                baseActivity?.showProgress()
            }else{
                baseActivity?.hideProgress()
            }
        })
        viewmodel?.updateResponse?.observe(viewLifecycleOwner, Observer {
            CommonUtils.showdialog(it.message,activity as BaseActivity,false)
            var dataObj=AppPreference(activity).getUserObject()
            dataObj.data?.userAvatar=it.data?.userAvatar
            AppPreference(activity).setUserObject(dataObj)
        })
    }

    fun getEncoded64ImageStringFromBitmap(bitmap: Bitmap): String? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.JPEG, 70, stream)
        val byteFormat = stream.toByteArray()
        return Base64.encodeToString(byteFormat, Base64.NO_WRAP)
    }

    fun setprofileInfo(){
        userName.text= AppPreference(activity).getUserObject().data?.firstName+" "+
                AppPreference(activity).getUserObject().data?.lastName
        mobile.text=AppPreference(activity).getUserObject().data?.phoneNumber
        emailId.text=AppPreference(activity).getUserObject().data?.email
        iv_user_img.loadImg(AppPreference(activity).getUserObject().data?.userAvatar)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refresh(data: LoginResponse)
    {
        setprofileInfo()
    }

}