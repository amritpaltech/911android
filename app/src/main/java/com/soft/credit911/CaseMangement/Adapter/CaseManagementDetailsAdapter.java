package com.soft.credit911.CaseMangement.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.soft.credit911.CaseMangement.Activity.CaseManagementActivity;
import com.soft.credit911.CaseMangement.Model.CashDemoModel;
import com.soft.credit911.R;
import com.soft.credit911.databinding.CaseManagementItemListBinding;

import java.util.List;

public class CaseManagementDetailsAdapter extends RecyclerView.Adapter<CaseManagementDetailsAdapter.CaseManagementViewHolder> {
    private List<CashDemoModel> cashDemoModels;
    Context context;

    public CaseManagementDetailsAdapter(List<CashDemoModel> cashDemoModels, Context context) {
        this.cashDemoModels = cashDemoModels;
        this.context = context;
    }

    @Override
    public CaseManagementDetailsAdapter.CaseManagementViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.case_management_item_list,viewGroup,false);
        return new CaseManagementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CaseManagementDetailsAdapter.CaseManagementViewHolder holder, int position) {
        CashDemoModel cashDemoModelList= cashDemoModels.get(position);
        holder.binding.tvBankName.setText(cashDemoModelList.getBankName());
        holder.binding.tvMark.setText(cashDemoModelList.getMark());
        holder.binding.tvStatus.setText(cashDemoModelList.getStatus());
        holder.binding.tvDate.setText(cashDemoModelList.getDate());
        holder.binding.tvUserStatus.setText(cashDemoModelList.getUserStatus());
        holder.binding.tvAgentName.setText(cashDemoModelList.getAgentName());
        holder.binding.tvUserMessage.setText(cashDemoModelList.getUserMessage());
        Glide.with(context)
                .load(R.drawable.chris_hemsworth)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(500)))
                .placeholder(R.drawable.chris_hemsworth)
                .into(holder.binding.ivUser);

        holder.binding.cassManagementMainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, CaseManagementActivity.class);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return cashDemoModels.size();
    }

    public class CaseManagementViewHolder extends RecyclerView.ViewHolder {
        private CaseManagementItemListBinding binding;

        public CaseManagementViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
