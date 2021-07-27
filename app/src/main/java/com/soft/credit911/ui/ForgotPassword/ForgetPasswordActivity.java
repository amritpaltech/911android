package com.soft.credit911.ui.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.soft.credit911.datamodel.ForgotPasswordResponse;
import com.soft.credit911.ui.ForgotPassword.mvp.ForgotPasswordPresenter;
import com.soft.credit911.ui.ForgotPassword.mvp.ForgotPasswordView;
import com.soft.credit911.Utils.AppConstants;
import com.soft.credit911.Utils.CommonUtils;
import com.soft.credit911.databinding.ActivityForgetPasswordBinding;
import com.soft.credit911.databinding.ToolbarBinding;

import static com.soft.credit911.Utils.CommonUtils.isValidEmail;

public class ForgetPasswordActivity extends AppCompatActivity implements ForgotPasswordView {

    private ActivityForgetPasswordBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    private ForgotPasswordPresenter forgotPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Forgot Password");
        toolbarBinding.navigationIcon.setOnClickListener(v -> {
            onBackPressed();
        });
        View view = layoutBinding.getRoot();
        setContentView(view);
        forgotPasswordPresenter = new ForgotPasswordPresenter(this, this);
        layoutBinding.tvResetPassword.setOnClickListener(v -> {
            if (validate())
                forgotPasswordPresenter.forgotPassword(layoutBinding.etUserEmail.getText().toString().trim());
        });
    }

    private boolean validate() {
        if (layoutBinding.etUserEmail.getText().toString().trim().equals("")) {
            layoutBinding.etUserEmail.setError("Enter Your Email Id");
            layoutBinding.etUserEmail.requestFocus();
            return false;
        }

        if (!isValidEmail(layoutBinding.etUserEmail.getText().toString().trim())) {
            layoutBinding.etUserEmail.setError("Invalid Email Id");
            layoutBinding.etUserEmail.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void forgotPasswordResponse(ForgotPasswordResponse forgotPasswordResponse) {
        CommonUtils.showdialog(forgotPasswordResponse.getMessage(), this, forgotPasswordResponse.getStatus().equals(AppConstants.API_SUCCESS));
//Toast.makeText(this, forgotPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }
}