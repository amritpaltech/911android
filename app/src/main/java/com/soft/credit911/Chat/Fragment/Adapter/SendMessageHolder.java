package com.soft.credit911.Chat.Fragment.Adapter;

import android.view.View;
import android.widget.TextView;

import com.soft.credit911.Chat.Fragment.Model.ChatObject;
import com.soft.credit911.R;

public class SendMessageHolder extends MessageViewHolder {
    TextView sendMessage, messageTime;


    public SendMessageHolder(View view) {
        super(view);
        this.sendMessage = view.findViewById(R.id.tv_send_message);
    }

    @Override
    public void onBindView(ChatObject object) {
        this.sendMessage.setText(object.getText());
    }


}

