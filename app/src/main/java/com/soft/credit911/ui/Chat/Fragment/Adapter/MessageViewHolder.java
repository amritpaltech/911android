package com.soft.credit911.ui.Chat.Fragment.Adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import com.soft.credit911.ui.Chat.Fragment.Model.ChatObject;

public abstract class MessageViewHolder extends RecyclerView.ViewHolder {

    MessageViewHolder(View view) {
        super(view);
    }
    public abstract void onBindView(ChatObject object);
}
