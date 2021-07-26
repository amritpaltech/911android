package com.soft.credit911.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.soft.credit911.Landing.LandingActivity;
import com.soft.credit911.Login.LoginActivity;
import com.soft.credit911.Utils.AppPreference;
import com.soft.credit911.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding layoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        layoutBinding.tvGetStartNow.setOnClickListener(v -> {
            Intent intent;
            if (new AppPreference(this).getIsLogin())
                intent = new Intent(this, LandingActivity.class);
            else
                intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

    }
}