package com.soft.credit911.ui.Notification.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.soft.credit911.ui.Notification.Adapter.NotificationDetailsAdapter;
import com.soft.credit911.datamodel.NotificationModel;
import com.soft.credit911.databinding.ActivityNotificationBinding;
import com.soft.credit911.databinding.ToolbarBinding;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    private NotificationDetailsAdapter notificationDetailsAdapter;
    private ArrayList<NotificationModel> notificationModels = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityNotificationBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);

        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Notification");
        toolbarBinding.navigationIcon.setOnClickListener(v -> {
            onBackPressed();
        });

        intiView();

    }

    private void intiView() {


        notificationDetailsAdapter = new NotificationDetailsAdapter(notificationModels, this);
        layoutBinding.rvNotification.setAdapter(notificationDetailsAdapter);
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setUserNotification("Upload your Driving Licence");
        notificationModel.setStartTime("june 6 at");
        notificationModel.setEndTime("12:00Pm");
        notificationModels.add(notificationModel);
        notificationModel.setUserNotification("Update your Profile");
        notificationModel.setStartTime("june10 at");
        notificationModel.setEndTime("12:00Pm");
        notificationModels.add(notificationModel);
    }
}