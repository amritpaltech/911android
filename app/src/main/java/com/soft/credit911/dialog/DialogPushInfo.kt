package com.soft.credit911.dialog

import android.view.View
import com.soft.credit911.R
import kotlinx.android.synthetic.main.dialog_push_info.*
import org.json.JSONObject


class DialogPushInfo(val  onClick: (op:Int) -> Unit) : BaseFragmentDialog() {


    var data:String?=null
    override fun onCreated() {
        onClickEvents()
        setData()
    }

    fun setPopupInfo(data:String){
        this.data=data
    }
    override fun getLayoutID(): Int {
        return R.layout.dialog_push_info
    }

    fun onClickEvents()
    {

        tv_Documents.setOnClickListener {
            dismiss()
        }

    }

    fun setData()
    { val obj:JSONObject= JSONObject(data)
        title.text=""+obj.getString("title")
        message.text=""+obj.getString("message")
        imageAnim.visibility= View.GONE
        if(obj.getString("status").equals("congrats")){
            animationView.setAnimation("congratulations.json")
            imageAnim.visibility= View.VISIBLE
        }
        else if(obj.getString("status").equals("warning")){
            animationView.setAnimation("warn.json")
        }
        else if(obj.getString("status").equals("notice")){
            animationView.setAnimation("notice.json")
        }
        else{
            animationView.setAnimation("notice.json")
        }

        animationView.loop(true)
        animationView.playAnimation()
    }
}