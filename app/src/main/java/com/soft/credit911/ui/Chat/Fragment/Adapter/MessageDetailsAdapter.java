package com.soft.credit911.ui.Chat.Fragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.soft.credit911.ui.Chat.Fragment.Model.ChatObject;
import com.soft.credit911.R;

import java.util.ArrayList;

public class MessageDetailsAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    public ArrayList<ChatObject> chatObjects = new ArrayList<>();
    Context context;

    public void add(ArrayList<ChatObject> chatObjects) {
        this.chatObjects = chatObjects;
        notifyDataSetChanged();
    }

    public MessageDetailsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        switch (viewType) {
            case ChatObject.SEND_MESSAGE:
                itemView = inflater.inflate(R.layout.send_message, parent, false);
                return new SendMessageHolder(itemView);

            case ChatObject.RECEIVED_MESSAGE:
                itemView = inflater.inflate(R.layout.received_message, parent, false);
                return new ReceivedMessageHolder(itemView,context);

            default:
                itemView = inflater.inflate(R.layout.received_message, parent, false);
                return new ReceivedMessageHolder(itemView, context);
        }
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.onBindView(chatObjects.get(position));

    }

    @Override
    public int getItemViewType(int position) {
        return chatObjects.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return (chatObjects == null) ? 0 : chatObjects.size();
    }
}
