package com.soft.credit911.ui.MyProfile.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.gson.JsonParser
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.datamodel.LoginResponse
import com.soft.credit911.fcm.notificationObject
import com.soft.credit911.ui.dashboard.UserProfile.Fragment.ProfileViewModel
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.json.JSONObject

class MyProfileActivity : SubBaseActivity() {

    var mViewModel: ProfileViewModel? = null
    
    override fun getLayoutID(): Int {
        return R.layout.activity_my_profile
    }

    override fun onViewCreated() {
        toolbarTitle.text = "My Profile"
        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        initView()
        setProfileData()
        mViewModel= ProfileViewModel()
        attachObserver()
    }


    fun setProfileData(){
        et_first_name.setText(AppPreference(this).getUserObject().data?.firstName)
        etLastName.setText(AppPreference(this).getUserObject().data?.lastName)
        etPhone.setText(AppPreference(this).getUserObject().data?.phoneNumber)
        et_email.setText(AppPreference(this).getUserObject().data?.email)
        et_city.setText(AppPreference(this).getUserObject().data?.city)
        et_state.setText(AppPreference(this).getUserObject().data?.state)
        et_Zip.setText(AppPreference(this).getUserObject().data?.postal_code)
        et_address1.setText(AppPreference(this).getUserObject().data?.address_line_1)
        et_address2.setText(AppPreference(this).getUserObject().data?.address_line_2)

        if(intent.extras?.containsKey("pushData")==true){
            pushDataMain = intent?.getSerializableExtra("pushData") as notificationObject
            showPushDialog()
        }
    }

    private fun initView() {
        tvSave.setOnClickListener { v: View? ->
            if (isValid) {
                val mJsObjParam = JSONObject()
                mJsObjParam.put("first_name", et_first_name.text.toString().trim ())
                mJsObjParam.put("last_name",  etLastName.text.toString().trim ())
                mJsObjParam.put("phone_number",  etPhone.text.toString().trim ())
                val myOb = JsonParser().parse(mJsObjParam.toString()).asJsonObject
                mViewModel?.updateUserInfo(myOb)
            }
        }
    }

    private val isValid: Boolean
        private get() {
            if (et_first_name.text.toString().trim { it <= ' ' } == "") {
                et_first_name.error = resources.getString(R.string.first_name_error)
                et_first_name.requestFocus()
                return false
            }
            if (etLastName.text.toString().trim { it <= ' ' } == "") {
                etLastName.error = resources.getString(R.string.last_name_error)
                etLastName.requestFocus()
                return false
            }
            if (etPhone.text.toString().trim { it <= ' ' } == "") {
                etPhone.error = resources.getString(R.string.phone_number_error)
                etPhone.requestFocus()
                return false
            }
            return true
        }

    fun attachObserver(){
        mViewModel?.updateProfileResponse?.observe(this, Observer {myProfileResponse->
            CommonUtils.showdialog(myProfileResponse.message, this, false)
            var dataObj:LoginResponse=AppPreference(this).getUserObject()
            dataObj.data?.firstName=et_first_name.text.toString()
            dataObj.data?.lastName=etLastName.text.toString()
            dataObj.data?.phoneNumber=etPhone.text.toString()
            AppPreference(this).setUserObject(dataObj)
            EventBus.getDefault().post(dataObj)
        })
        mViewModel?.isLoading?.observe(this, Observer {
            if(it){
               showProgress()
            }else{
               hideProgress()
            }
        })
    }

}