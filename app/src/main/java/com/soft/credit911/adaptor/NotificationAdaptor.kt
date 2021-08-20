package com.soft.credit911.adaptor

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.R
import com.soft.credit911.Utils.loadImg
import com.soft.credit911.datamodel.data_cases
import com.soft.credit911.datamodel.data_docs
import com.soft.credit911.datamodel.data_notification
import kotlinx.android.synthetic.main.notification_item_list.view.*


class NotificationAdaptor(val dataList: ArrayList<data_notification.AppNotification>,
                          private val onItemClick: (data_list: data_notification.AppNotification) -> Unit) :
    RecyclerView.Adapter<NotificationAdaptor.ViewHolder>() {

    var mContext: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.notification_item_list, parent, false)
        mContext = parent.context
        return ViewHolder(v);
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind() {
            val data= dataList.get(adapterPosition)
            itemView.tv_user_notification.text = data.description
            itemView.tv_start_time.text = data.created_at
            itemView?.setOnClickListener {
                onItemClick( dataList.get(adapterPosition))
            }




        }


    }
}





