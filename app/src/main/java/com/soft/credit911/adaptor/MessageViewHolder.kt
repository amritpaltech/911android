package com.soft.credit911.adaptor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.datamodel.ChatObject

abstract class MessageViewHolder internal constructor(view: View?) : RecyclerView.ViewHolder(
    view!!
) {
    abstract fun onBindView(`object`: ChatObject?)
}