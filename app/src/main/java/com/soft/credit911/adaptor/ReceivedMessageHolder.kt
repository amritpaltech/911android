package com.soft.credit911.adaptor

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.soft.credit911.R
import com.soft.credit911.datamodel.ChatObject

class ReceivedMessageHolder(view: View, var context: Context) : MessageViewHolder(view) {
    var receivedMessage: TextView
    var receivedMessageTime: TextView
    var receivedUserProfile: ImageView
    override fun onBindView(`object`: ChatObject?) {
        receivedMessage.text = `object`!!.text
        receivedMessageTime.text = `object`.userTime
    }

    init {
        receivedMessage = view.findViewById(R.id.tv_received_message)
        receivedMessageTime = view.findViewById(R.id.tv_received_time)
        receivedUserProfile = view.findViewById(R.id.iv_received_user_profile)
    }
}