package com.soft.credit911.CaseMangement.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.soft.credit911.R;
import com.soft.credit911.databinding.ActivityCaseManagementBinding;
import com.soft.credit911.databinding.ActivityNotificationBinding;
import com.soft.credit911.databinding.ToolbarBinding;

public class CaseManagementActivity extends AppCompatActivity {
    private ActivityCaseManagementBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityCaseManagementBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);


        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Case Management");
        toolbarBinding.navigationIcon.setOnClickListener(v -> {
            onBackPressed();
        });

        Glide.with(this)
                .load(R.drawable.chris_hemsworth)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(500)))
                .placeholder(R.drawable.chris_hemsworth)
                .into(layoutBinding.ivUser);
    }
}