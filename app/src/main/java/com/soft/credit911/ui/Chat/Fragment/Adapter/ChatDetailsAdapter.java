package com.soft.credit911.ui.Chat.Fragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.soft.credit911.ui.Chat.Activity.ChatActivity;
import com.soft.credit911.ui.Chat.Fragment.Model.DemoModel;
import com.soft.credit911.R;
import com.soft.credit911.databinding.ChatItemListBinding;

import java.util.List;

public class ChatDetailsAdapter extends RecyclerView.Adapter<ChatDetailsAdapter.ChatViewHolder> {
    private List<DemoModel> demoModels;
    Context context;

    public ChatDetailsAdapter(List<DemoModel> demoModelList, Context context) {
        this.demoModels = demoModelList;
        this.context = context;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ChatItemListBinding chatItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.chat_item_list, viewGroup, false);
        return new ChatViewHolder(chatItemListBinding);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        DemoModel demoModelList = demoModels.get(position);
        holder.chatItemListBinding.tvUserName.setText(demoModelList.getUserName());
        holder.chatItemListBinding.tvMessageDescription.setText(demoModelList.getMessageDescription());
        holder.chatItemListBinding.tvTime.setText(demoModelList.getTime());
        holder.chatItemListBinding.tvMessageCount.setText(demoModelList.getMessageCount());
        holder.chatItemListBinding.chatMainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);
            context.startActivity(intent);
        });
        Glide.with(context)
                .load(R.drawable.chris_hemsworth)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(150)))
                .placeholder(R.drawable.chris_hemsworth)
                .into(holder.chatItemListBinding.ivUser);

    }


    @Override
    public int getItemCount() {
        return demoModels.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private ChatItemListBinding chatItemListBinding;

        public ChatViewHolder(ChatItemListBinding chatItemListBinding) {
            super(chatItemListBinding.getRoot());
            this.chatItemListBinding = chatItemListBinding;
        }
    }
}
