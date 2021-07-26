package com.soft.credit911.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.soft.credit911.Landing.LandingActivity;
import com.soft.credit911.ForgotPassword.ForgetPasswordActivity;
import com.soft.credit911.Login.Adpater.ViewPagerAdapter;
import com.soft.credit911.Login.Model.LoginResponse;
import com.soft.credit911.Login.mvp.LoginPresenter;
import com.soft.credit911.Login.mvp.LoginView;
import com.soft.credit911.OTPVerification.LoginVerificationActivity;
import com.soft.credit911.R;
import com.soft.credit911.Utils.AppConstants;
import com.soft.credit911.Utils.AppPreference;
import com.soft.credit911.Utils.CommonUtils;
import com.soft.credit911.databinding.ActivityMainBinding;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.soft.credit911.Utils.CommonUtils.isValidEmail;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private ActivityMainBinding layoutBinding;
    LoginPresenter loginPresenter;
    AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);
        loginPresenter = new LoginPresenter(this, this);
        appPreference = new AppPreference(this);
        initView();
    }

    private void initView() {
        layoutBinding.tvLogin.setOnClickListener(v -> {
            if (validate())
                loginPresenter.signIn(layoutBinding.etUserEmail.getText().toString().trim(), layoutBinding.etUserPassword.getText().toString().trim());
        });

        layoutBinding.tvForgetYourPassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForgetPasswordActivity.class);
            startActivity(intent);
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        layoutBinding.viewPager.setAdapter(adapter);
        layoutBinding.viewPager.setCurrentItem(0);
        layoutBinding.tutorialSliderTab.setupWithViewPager(layoutBinding.viewPager);


        /*layoutBinding.tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
        });*/
    }

    private boolean validate() {
        if (layoutBinding.etUserEmail.getText().toString().trim().equals("")) {
            layoutBinding.etUserEmail.setError(getResources().getString(R.string.email_error));
            layoutBinding.etUserEmail.requestFocus();
            return false;
        }

        if (!isValidEmail(layoutBinding.etUserEmail.getText().toString().trim())) {
            layoutBinding.etUserEmail.setError(getResources().getString(R.string.invalid_email_error));
            layoutBinding.etUserEmail.requestFocus();
            return false;
        }
        if (layoutBinding.etUserPassword.getText().toString().trim().equals("")) {
            layoutBinding.etUserPassword.setError(getResources().getString(R.string.your_password));
            layoutBinding.etUserPassword.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void LoginResponse(LoginResponse loginResponse) {
        if (loginResponse.getStatus().equals(AppConstants.API_SUCCESS)) {
            String dtStart = loginResponse.getData().getToken2faExpiry();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar today = Calendar.getInstance();

            Date expireDate = null;
            try {
                expireDate = format.parse(dtStart);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            new AppPreference(this).setUserObject(loginResponse);
            if (expireDate.after(today.getTime()) && appPreference.getUserLoggedIn().contains(String.valueOf(loginResponse.getData().getId()))) {
                appPreference.setIsLogin(true);
                Intent intent = new Intent(this, LandingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, LoginVerificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        } else {
            CommonUtils.showdialog(loginResponse.getMessage(), this, false);
        }
    }
}