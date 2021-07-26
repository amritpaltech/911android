package com.soft.credit911.Chat.Fragment.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.soft.credit911.Chat.Fragment.Model.ChatObject;
import com.soft.credit911.R;

public class ReceivedMessageHolder extends MessageViewHolder {
    TextView receivedMessage, receivedMessageTime;
    ImageView receivedUserProfile;
    Context context;

    public ReceivedMessageHolder(View view, Context context) {
        super(view);
        this.context=context;
        this.receivedMessage = view.findViewById(R.id.tv_received_message);
        this.receivedMessageTime = view.findViewById(R.id.tv_received_time);
        this.receivedUserProfile = view.findViewById(R.id.iv_received_user_profile);
    }

    @Override
    public void onBindView(ChatObject object) {
        this.receivedMessage.setText(object.getText());
        this.receivedMessageTime.setText(object.getUserTime());

        Glide.with(context)
                .load(R.drawable.chris_hemsworth)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(250)))
                .placeholder(R.drawable.chris_hemsworth)
                .into(this.receivedUserProfile);
    }
}
