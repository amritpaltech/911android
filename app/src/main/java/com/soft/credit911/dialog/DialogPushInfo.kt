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
//        imageAnim.visibility= View.GONE
        if(obj.getString("type").equals("success")){
            if(obj.getInt("is_congrats")==1) {
                animationView.setAnimation("congratulations.json")
            }
            imageAnim.visibility= View.VISIBLE
        }
        else if(obj.getString("type").equals("warning")){
//            animationView.setAnimation("warn.json")
            imageAnim.setImageResource(R.drawable.ic_baseline_warning_24)
            imageAnim.visibility= View.VISIBLE
        }
        else if(obj.getString("type").equals("notice")){
//            animationView.setAnimation("notice.json")
            imageAnim.setImageResource(R.drawable.notice)
            imageAnim.visibility= View.VISIBLE
        }
        else{
            imageAnim.setImageResource(R.drawable.notice)
            imageAnim.visibility= View.VISIBLE
        }

        animationView.loop(true)
        animationView.playAnimation()
    }
}