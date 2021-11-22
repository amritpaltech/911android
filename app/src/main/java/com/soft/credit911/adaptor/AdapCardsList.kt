package com.soft.credit911.adaptor

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soft.credit911.R
import kotlinx.android.synthetic.main.item_expandable_list.view.*
import kotlinx.android.synthetic.main.item_overview.view.*


class AdapCardsList() : RecyclerView.Adapter<AdapCardsList.ViewHolder>() {

    var context: Context? = null
    var list=ArrayList<String>()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent?.context
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_expandable_list, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return 6
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

            itemView.setOnClickListener {

                if (itemView.layout_overview.visibility==View.VISIBLE)
                {
                    itemView.layout_overview.visibility=View.GONE
                }else
                {
                    itemView.layout_overview.visibility=View.VISIBLE
                }

                itemView.listAlpha.adapter= AdapHistory(list)


            }


        }


    }

    fun addListItems()
    {
        list.add("J")
        list.add("M")
        list.add("A")
        list.add("M")
        list.add("J")
        list.add("J")
        list.add("A");
        list.add("S");
        list.add("O");
        list.add("D");



    }
}




