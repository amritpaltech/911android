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
import kotlinx.android.synthetic.main.item_case_matters.view.*


class CaseMatterAdaptor(val dataList: ArrayList<data_cases.Matters>,
                        private val onItemClick: (data_list: data_cases.Cases) -> Unit) : RecyclerView.Adapter<CaseMatterAdaptor.ViewHolder>() {

    var mContext: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_case_matters, parent, false)
        mContext = parent.context
        return ViewHolder(v);
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind() {
            val data= dataList.get(adapterPosition)
            itemView.tv_bank_name.text=data.name
            itemView.iv_user.loadImg(data.agent_details?.avatar)
            itemView.tv_user_message.text=data.agent_details?.first_name+" "+data.agent_details?.last_name
        }


    }
}





