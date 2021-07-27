package com.soft.credit911.adaptor

import android.view.View
import android.widget.TextView
import com.soft.credit911.R
import com.soft.credit911.datamodel.ChatObject

class SendMessageHolder(view: View) : MessageViewHolder(view) {
    var sendMessage: TextView
    var messageTime: TextView? = null
    override fun onBindView(`object`: ChatObject?) {
        sendMessage.text = `object`!!.text
    }

    init {
        sendMessage = view.findViewById(R.id.tv_send_message)
    }
}