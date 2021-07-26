package com.soft.credit911.SecurityQuestions.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.soft.credit911.Dashboard.fragment.DashboardFragment;
import com.soft.credit911.Landing.LandingActivity;
import com.soft.credit911.R;
import com.soft.credit911.databinding.ActivityCongratulationsBinding;
import com.soft.credit911.databinding.ActivityErrrorBinding;

public class ErrorActivity extends AppCompatActivity {
    private ActivityErrrorBinding layoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityErrrorBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);

        layoutBinding.tvMessage.setText(getIntent().getStringExtra("message"));
        layoutBinding.tvGoToDashBoard.setOnClickListener(v -> {
            Intent intent = new Intent(this, DashboardFragment.class);
            startActivity(intent);
        });

        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {
                layoutBinding.tvGoToDashBoard.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Intent intent = new Intent(ErrorActivity.this, LandingActivity.class);
                intent.putExtra("isError",1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        }.start();
    }
}