package com.soft.credit911.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.R
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.datamodel.data_notification
import kotlinx.android.synthetic.main.item_credit_history.view.*
import java.text.SimpleDateFormat
import java.util.*


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
            itemView.dateText.text = getDateString(myDate)
            itemView.scoreText.text = data.score

        }


    }

    fun getDateString(tdate: Date?): String? {
        var datetext = ""
        //Grab the day of the month
        val DayOfMonth = SimpleDateFormat("d")
        val FormattedDate = SimpleDateFormat()
        val n=DayOfMonth.format(tdate).toString().toInt()
        if (n >= 11 && n <= 13) {
            datetext= "th";
        }
        else {
            datetext = when (n % 10) {
                1 ->             //Return st
                    "st"
                2 ->             //Return nd
                    "nd"
                3 -> "rd"
                else -> "th"
            }
        }
        FormattedDate.applyPattern("d'$datetext' MMM, y")
        return FormattedDate.format(tdate)
    }

}





