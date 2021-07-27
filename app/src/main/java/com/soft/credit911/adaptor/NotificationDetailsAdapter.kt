package com.soft.credit911.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.R
import com.soft.credit911.adaptor.NotificationDetailsAdapter.NotificationViewHolder
import com.soft.credit911.databinding.NotificationItemListBinding
import com.soft.credit911.datamodel.NotificationModel

class NotificationDetailsAdapter(
    var notificationModels: List<NotificationModel>,
    var context: Context
) : RecyclerView.Adapter<NotificationViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): NotificationViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.notification_item_list, viewGroup, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notificationModelList = notificationModels[position]
        holder.binding!!.tvUserNotification.text = notificationModelList.userNotification
        holder.binding!!.tvStartTime.text = notificationModelList.startTime
        holder.binding!!.tvEndTime.text = notificationModelList.endTime
    }

    override fun getItemCount(): Int {
        return notificationModels.size
    }

    inner class NotificationViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var binding: NotificationItemListBinding?

        init {
            binding = DataBindingUtil.bind(itemView!!)
        }
    }
}