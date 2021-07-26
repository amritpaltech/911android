package com.soft.credit911.SecurityQuestions.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.soft.credit911.Dashboard.fragment.DashboardFragment;
import com.soft.credit911.Landing.LandingActivity;
import com.soft.credit911.R;
import com.soft.credit911.databinding.ActivityCongratulationsBinding;
import com.soft.credit911.databinding.ActivitySecurityQuestionsBinding;

public class CongratulationsActivity extends AppCompatActivity {
    private ActivityCongratulationsBinding layoutBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityCongratulationsBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);
        layoutBinding.tvMesssage.setText(getIntent().getStringExtra("message"));
        layoutBinding.tvGoToDashBoard.setOnClickListener(v -> {
            Intent intent = new Intent(this, LandingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}