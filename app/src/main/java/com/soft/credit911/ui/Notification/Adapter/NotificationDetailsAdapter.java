package com.soft.credit911.ui.Notification.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.credit911.R;
import com.soft.credit911.datamodel.NotificationModel;
import com.soft.credit911.databinding.NotificationItemListBinding;

import java.util.List;

public class NotificationDetailsAdapter extends RecyclerView.Adapter<NotificationDetailsAdapter.NotificationViewHolder> {
    public List<NotificationModel> notificationModels;
    Context context;

    public NotificationDetailsAdapter(List<NotificationModel> notificationModels, Context context) {
        this.notificationModels = notificationModels;
        this.context = context;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.notification_item_list, viewGroup, false);
        return new NotificationViewHolder(view);

    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        NotificationModel notificationModelList = notificationModels.get(position);

        holder.binding.tvUserNotification.setText(notificationModelList.getUserNotification());
        holder.binding.tvStartTime.setText(notificationModelList.getStartTime());
        holder.binding.tvEndTime.setText(notificationModelList.getEndTime());
    }

    @Override
    public int getItemCount() {
        return notificationModels.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        public NotificationItemListBinding binding;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
