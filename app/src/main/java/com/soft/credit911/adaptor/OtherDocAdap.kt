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
import kotlinx.android.synthetic.main.other_document_item_list.view.*


class OtherDocAdap(val dataList: ArrayList<data_docs.DocData>,
                   private val onItemClick: (data_list: data_docs.DocData) -> Unit) : RecyclerView.Adapter<OtherDocAdap.ViewHolder>() {

    var mContext: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.other_document_item_list, parent, false)
        mContext = parent.context
        return ViewHolder(v);
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind() {
            val data= dataList.get(adapterPosition)
            itemView.tv_user_licence.text=data.Label
            itemView.tv_user_status.text=data.message
        //    itemView.tv_user_status.text=if (data.description!=null && data.description.length>0) data.description else "..."

            when(data.status){
                "missing"->{
                    itemView.mainLayout_ll.setBackgroundColor(Color.parseColor("#f4f9f3"))
                    itemView.iv_check.setColorFilter(Color.parseColor("#f5c242"))
                    itemView.iv_check.setImageResource(R.drawable.ic__check_circle)
                    itemView.tv_user_licence.setTextColor(Color.parseColor("#d4d4d4"))
                    itemView.tv_user_status.setTextColor(Color.parseColor("#f58442"))
                    itemView.tv_user_status.text=data.message
//                    itemView.tv_user_status.text="..."
                }

                "pending"->{
                    itemView.mainLayout_ll.setBackgroundColor(Color.parseColor("#f4f9f3"))
                    itemView.iv_check.setColorFilter(Color.parseColor("#d4d4d4"))
                    itemView.iv_check.setImageResource(R.drawable.ic__check_circle)
                    itemView.tv_user_licence.setTextColor(Color.parseColor("#d4d4d4"))
                    itemView.tv_user_status.setTextColor(Color.parseColor("#f59342"))
//                    itemView.tv_user_status.text="Pending Approval"
                }

                "approved"->{
                    itemView.mainLayout_ll.setBackgroundColor(Color.parseColor("#e3f1e4"))
                    itemView.iv_check.setColorFilter(Color.parseColor("#309f7e"))
                    itemView.iv_check.setImageResource(R.drawable.ic_check_circle_green)
                    itemView.tv_user_licence.setTextColor(Color.BLACK)
                    itemView.tv_user_status.setTextColor(Color.parseColor("#309f7e"))
//                    itemView.tv_user_status.text=""
                }
                "rejected"->{
                    itemView.mainLayout_ll.setBackgroundColor(Color.parseColor("#c93b2b"))
                    itemView.iv_check.setColorFilter(Color.WHITE)
                    itemView.iv_check.setImageResource(R.drawable.ic_exclamation_triangle)
                    itemView.tv_user_licence.setTextColor(Color.WHITE)
                    itemView.tv_user_status.setTextColor(Color.WHITE)
//                    itemView.tv_user_status.text="Rejected"
                }
            }


            itemView?.setOnClickListener {

                onItemClick( dataList.get(adapterPosition))
            }




        }


    }
}





