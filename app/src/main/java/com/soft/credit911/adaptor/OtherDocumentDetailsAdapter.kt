package com.soft.credit911.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soft.credit911.R
import com.soft.credit911.adaptor.OtherDocumentDetailsAdapter.OtherDocumentDetailsViewHolder
import com.soft.credit911.databinding.OtherDocumentItemListBinding
import com.soft.credit911.datamodel.OtherDocumentModel
import java.util.*

class OtherDocumentDetailsAdapter(var context: Context) :
    RecyclerView.Adapter<OtherDocumentDetailsViewHolder>() {
    private var otherDocumentModels: List<OtherDocumentModel> = ArrayList()
    fun addList(otherDocumentModelArrayList: ArrayList<OtherDocumentModel>) {
        otherDocumentModels = otherDocumentModelArrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): OtherDocumentDetailsViewHolder {
        val otherDocumentItemListBinding: OtherDocumentItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.other_document_item_list, viewGroup, false
        )
        return OtherDocumentDetailsViewHolder(otherDocumentItemListBinding)
    }

    override fun onBindViewHolder(holder: OtherDocumentDetailsViewHolder, position: Int) {
        val otherDocumentList = otherDocumentModels[position]
        holder.binding.tvUserLicence.text = otherDocumentList.userLicence
        holder.binding.tvUserStatus.text = otherDocumentList.userStatus
        Glide.with(context)
            .load(otherDocumentList.checkImage)
            .placeholder(R.drawable.ic__check_circle)
            .into(holder.binding.ivCheck)
    }

    override fun getItemCount(): Int {
        return otherDocumentModels.size
    }

    inner class OtherDocumentDetailsViewHolder(var binding: OtherDocumentItemListBinding) :
        RecyclerView.ViewHolder(
            binding.root
        )
}