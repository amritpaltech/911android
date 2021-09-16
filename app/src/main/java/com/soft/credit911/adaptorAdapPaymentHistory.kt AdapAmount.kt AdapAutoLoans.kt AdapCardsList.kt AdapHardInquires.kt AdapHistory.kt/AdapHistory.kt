package com.utility.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marriageapp.R
import com.marriageapp.pojo.response.CameraItem
import com.marriageapp.pojo.response.CamerasOnSaleItem
import com.marriageapp.pojo.response.profile.PicturesItem
import com.marriageapp.pojo.response.profile.ProductsItem
import com.marriageapp.utils.DisplayUtils
import com.marriageapp.utils.loadImg
import kotlinx.android.synthetic.main.item_images.view.*
import kotlinx.android.synthetic.main.item_payment_history.view.*


class AdapHistory(val history: ArrayList<String>) : RecyclerView.Adapter<AdapHistory.ViewHolder>() {

    var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent?.context
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_payment_history, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return history.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.imgCheck.visibility=View.GONE
            if (adapterPosition==5||adapterPosition==6||adapterPosition==7)
            itemView.imgCheck.visibility=View.VISIBLE
            itemView.title.text=history.get(adapterPosition)



        }


    }
}




