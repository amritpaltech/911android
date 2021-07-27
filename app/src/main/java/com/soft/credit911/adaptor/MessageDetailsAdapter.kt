package com.soft.credit911.adaptor

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.datamodel.ChatObject
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.soft.credit911.R
import java.util.ArrayList

class MessageDetailsAdapter(var context: Context) : RecyclerView.Adapter<MessageViewHolder>() {
    var chatObjects: ArrayList<ChatObject>? = ArrayList()
    fun add(chatObjects: ArrayList<ChatObject>?) {
        this.chatObjects = chatObjects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView: View
        return when (viewType) {
            ChatObject.SEND_MESSAGE -> {
                itemView = inflater.inflate(R.layout.send_message, parent, false)
                SendMessageHolder(itemView)
            }
            ChatObject.RECEIVED_MESSAGE -> {
                itemView = inflater.inflate(R.layout.received_message, parent, false)
                ReceivedMessageHolder(itemView, context)
            }
            else -> {
                itemView = inflater.inflate(R.layout.received_message, parent, false)
                ReceivedMessageHolder(itemView, context)
            }
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.onBindView(chatObjects!![position])
    }

    override fun getItemViewType(position: Int): Int {
        return chatObjects!![position].type
    }

    override fun getItemCount(): Int {
        return if (chatObjects == null) 0 else chatObjects!!.size
    }
}