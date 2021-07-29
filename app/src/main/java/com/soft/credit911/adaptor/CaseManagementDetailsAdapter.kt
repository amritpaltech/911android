package com.soft.credit911.adaptor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.ui.casemanagement.CaseManagementActivity
import com.soft.credit911.R
import com.soft.credit911.adaptor.CaseManagementDetailsAdapter.CaseManagementViewHolder
import com.soft.credit911.databinding.CaseManagementItemListBinding
import com.soft.credit911.datamodel.CashDemoModel

class CaseManagementDetailsAdapter(
    private val cashDemoModels: List<CashDemoModel>,
    var context: Context
) : RecyclerView.Adapter<CaseManagementViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CaseManagementViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.case_management_item_list, viewGroup, false)
        return CaseManagementViewHolder(view)
    }

    override fun onBindViewHolder(holder: CaseManagementViewHolder, position: Int) {
        val cashDemoModelList = cashDemoModels[position]
        holder.binding!!.tvBankName.text = cashDemoModelList.bankName
        holder.binding.tvMark.text = cashDemoModelList.mark
        holder.binding.tvStatus.text = cashDemoModelList.status
        holder.binding.tvDate.text = cashDemoModelList.date
        holder.binding.tvUserStatus.text = cashDemoModelList.userStatus
        holder.binding.tvAgentName.text = cashDemoModelList.agentName
        holder.binding.tvUserMessage.text = cashDemoModelList.userMessage

        holder.binding.cassManagementMainLayout.setOnClickListener { v: View? ->
            val intent = Intent(context, CaseManagementActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cashDemoModels.size
    }

    inner class CaseManagementViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        val binding: CaseManagementItemListBinding?

        init {
            binding = DataBindingUtil.bind(itemView!!)
        }
    }
}