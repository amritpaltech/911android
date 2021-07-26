package com.soft.credit911.Document.Adapter;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.ViewGroup;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.soft.credit911.R;
import com.soft.credit911.Document.Model.OtherDocumentModel;
import com.soft.credit911.databinding.OtherDocumentItemListBinding;
import java.util.ArrayList;
import java.util.List;

public class OtherDocumentDetailsAdapter extends RecyclerView.Adapter<OtherDocumentDetailsAdapter.OtherDocumentDetailsViewHolder> {
    private List<OtherDocumentModel> otherDocumentModels = new ArrayList<>();
    Context context;

    public OtherDocumentDetailsAdapter(Context context) {
        this.context = context;
    }

    public void addList(ArrayList<OtherDocumentModel> otherDocumentModelArrayList) {
        this.otherDocumentModels = otherDocumentModelArrayList;
        notifyDataSetChanged();
    }

    @Override
    public OtherDocumentDetailsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        OtherDocumentItemListBinding otherDocumentItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.other_document_item_list, viewGroup, false);
        return new OtherDocumentDetailsViewHolder(otherDocumentItemListBinding);
    }

    @Override
    public void onBindViewHolder(OtherDocumentDetailsViewHolder holder, int position) {
        OtherDocumentModel otherDocumentList = otherDocumentModels.get(position);
        holder.binding.tvUserLicence.setText(otherDocumentList.getUserLicence());
        holder.binding.tvUserStatus.setText(otherDocumentList.getUserStatus());
        Glide.with(context)
                .load(otherDocumentList.getCheckImage())
                .placeholder(R.drawable.ic__check_circle)
                .into(holder.binding.ivCheck);

    }

    @Override
    public int getItemCount() {
        return otherDocumentModels.size();
    }

    public class OtherDocumentDetailsViewHolder extends RecyclerView.ViewHolder {
        public OtherDocumentItemListBinding binding;

        public OtherDocumentDetailsViewHolder(OtherDocumentItemListBinding otherDocumentItemListBinding) {
            super(otherDocumentItemListBinding.getRoot());
            binding = otherDocumentItemListBinding;
        }
    }
}
