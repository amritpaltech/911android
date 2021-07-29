package com.soft.credit911.adaptor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.R
import com.soft.credit911.adaptor.ChatDetailsAdapter.ChatViewHolder
import com.soft.credit911.databinding.ChatItemListBinding
import com.soft.credit911.ui.Chat.Activity.ChatActivity
import com.soft.credit911.datamodel.DemoModel

class ChatDetailsAdapter(private val demoModels: List<DemoModel>, var context: Context) :
    RecyclerView.Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChatViewHolder {
        val chatItemListBinding: ChatItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.chat_item_list, viewGroup, false
        )
        return ChatViewHolder(chatItemListBinding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val demoModelList = demoModels[position]
        holder.chatItemListBinding.tvUserName.text = demoModelList.userName
        holder.chatItemListBinding.tvMessageDescription.text = demoModelList.messageDescription
        holder.chatItemListBinding.tvTime.text = demoModelList.time
        holder.chatItemListBinding.tvMessageCount.text = demoModelList.messageCount
        holder.chatItemListBinding.chatMainLayout.setOnClickListener { v: View? ->
            val intent = Intent(context, ChatActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return demoModels.size
    }

    inner class ChatViewHolder(val chatItemListBinding: ChatItemListBinding) :
        RecyclerView.ViewHolder(
            chatItemListBinding.root
        )
}