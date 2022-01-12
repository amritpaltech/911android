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
import kotlinx.android.synthetic.main.activity_case_management.*
import kotlinx.android.synthetic.main.case_management_item_list.view.*


class CaseAdaptor(val dataList: ArrayList<data_cases.Cases>,
                  private val onItemClick: (data_list: data_cases.Cases) -> Unit) : RecyclerView.Adapter<CaseAdaptor.ViewHolder>() {

    var mContext: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.case_management_item_list, parent, false)
        mContext = parent.context
        return ViewHolder(v);
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind() {
            val data= dataList.get(adapterPosition)
            itemView.tv_bank_name.visibility=View.GONE
//            if( data?.matters?.size?:0>0){
                val adap = data?.matters?.let { CaseMatterAdaptor(data?.name?:"",it,{

                }) }
                itemView.listCase.adapter=adap
//            }
//            else{
//                itemView.tv_bank_name.visibility=View.VISIBLE
//            }

//            itemView.tv_bank_name.text=data.name
//            itemView.tv_user_message.text=data.description
//            itemView.tv_status.text=data.status
//            itemView.tv_mark.setColorFilter(Color.GRAY)
//            if(data.status.equals("assigned")){
//                itemView.tv_mark.setColorFilter(Color.YELLOW)
//            }else if(data.status.equals("open")){
//                itemView.tv_mark.setColorFilter(Color.GREEN)
//            }else if(data.status.equals("closed")){
//                itemView.tv_mark.setColorFilter(Color.BLUE)
//            }
//
//            itemView.tv_date.text=data.date
//            if(data.matters?.size?:0>0) {
//                itemView.tv_agent_name.text ="Agent: "+ data.matters?.get(0)?.agent_details?.first_name+ " "+data.matters?.get(0)?.agent_details?.last_name
//                itemView.iv_user.loadImg( data.matters?.get(0)?.agent_details?.avatar)
//            }
//            itemView?.setOnClickListener {
//
//                onItemClick( dataList.get(adapterPosition))
//            }
//



        }


    }
}





