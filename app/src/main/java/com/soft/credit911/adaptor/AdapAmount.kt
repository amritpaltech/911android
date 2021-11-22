package com.soft.credit911.adaptor

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soft.credit911.R


class AdapAmount() : RecyclerView.Adapter<AdapAmount.ViewHolder>() {

    var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent?.context
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_amount, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return 6
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {


        }


    }
}




