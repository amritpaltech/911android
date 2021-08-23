package com.soft.credit911.adaptor

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.R
import com.soft.credit911.Utils.loadImg
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.datamodel.data_cases
import com.soft.credit911.datamodel.data_docs
import com.soft.credit911.datamodel.data_notification
import kotlinx.android.synthetic.main.item_credit_history.view.*
import kotlinx.android.synthetic.main.notification_item_list.view.*
import kotlinx.android.synthetic.main.notification_item_list.view.tv_start_time
import kotlinx.android.synthetic.main.notification_item_list.view.tv_user_notification
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CreditHistoryListAdaptor(val dataList: ArrayList<DashboardResponse.CreditReportHistoryItem>,
                               private val onItemClick: (data_list: data_notification.AppNotification) -> Unit) :
    RecyclerView.Adapter<CreditHistoryListAdaptor.ViewHolder>() {

    var mContext: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_credit_history, parent, false)
        mContext = parent.context
        return ViewHolder(v);
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind() {
            val data= dataList.get(adapterPosition)
            val simpleDateFormat =
                SimpleDateFormat("yyyy-MM-dd")
            val myDate: Date = simpleDateFormat.parse(data.scoreDate)
            val simpleDateFormat2 =
                SimpleDateFormat("MMM dd, yyyy")


            itemView.dateText.text = simpleDateFormat2.format(myDate)
            itemView.scoreText.text = data.score

        }


    }
}





