package com.soft.credit911.dialog

import android.view.View
import com.soft.credit911.R
import kotlinx.android.synthetic.main.dialog_cred_info.*
import org.json.JSONObject


class DialogCredInfo() : BaseFragmentDialog() {


    var data:String?=null
    override fun onCreated() {
        onClickEvents()
        setData()
    }

    fun setPopupInfo(data:String){
        this.data=data
    }
    override fun getLayoutID(): Int {
        return R.layout.dialog_cred_info
    }

    fun onClickEvents()
    {

        tv_Documents.setOnClickListener {
            dismiss()
        }

    }

    fun setData()
    {


    }
}