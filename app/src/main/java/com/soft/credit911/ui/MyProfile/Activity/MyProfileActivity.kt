package com.soft.credit911.ui.MyProfile.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.databinding.ActivityMyProfileBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.datamodel.MyProfileResponse
import com.soft.credit911.ui.MyProfile.mvp.MyProfilePresenter
import com.soft.credit911.ui.MyProfile.mvp.MyProfileView

class MyProfileActivity : AppCompatActivity(), MyProfileView {
    private var layoutBinding: ActivityMyProfileBinding? = null
    private var toolbarBinding: ToolbarBinding? = null
    var myProfilePresenter: MyProfilePresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityMyProfileBinding.inflate(
            layoutInflater
        )
        val view = layoutBinding!!.root
        setContentView(view)
        myProfilePresenter = MyProfilePresenter(this, this as MyProfileView)
        toolbarBinding = layoutBinding!!.toolbarLayout
        toolbarBinding!!.toolbarTitle.text = "MyProfile"
        toolbarBinding!!.navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        initView()
    }

    private fun initView() {
        layoutBinding!!.tvSave.setOnClickListener { v: View? ->
            if (isValid) {
                myProfilePresenter!!.myProfile(
                    layoutBinding!!.etFirstName.text.toString().trim { it <= ' ' },
                    layoutBinding!!.etLastName.text.toString().trim { it <= ' ' },
                    layoutBinding!!.etPhone.text.toString().trim { it <= ' ' })
            }
        }
    }

    private val isValid: Boolean
        private get() {
            if (layoutBinding!!.etFirstName.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etFirstName.error = resources.getString(R.string.first_name_error)
                layoutBinding!!.etFirstName.requestFocus()
                return false
            }
            if (layoutBinding!!.etLastName.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etLastName.error = resources.getString(R.string.last_name_error)
                layoutBinding!!.etLastName.requestFocus()
                return false
            }
            if (layoutBinding!!.etPhone.text.toString().trim { it <= ' ' } == "") {
                layoutBinding!!.etPhone.error = resources.getString(R.string.phone_number_error)
                layoutBinding!!.etPhone.requestFocus()
                return false
            }
            return true
        }

    override fun MyProfileResponse(myProfileResponse: MyProfileResponse) {
        CommonUtils.showdialog(myProfileResponse.message, this, false)
    }
}