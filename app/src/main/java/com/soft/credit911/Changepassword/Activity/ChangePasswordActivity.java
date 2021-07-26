package com.soft.credit911.Changepassword.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.soft.credit911.R;
import com.soft.credit911.Changepassword.Model.ChangePasswordResponse;
import com.soft.credit911.Changepassword.mvp.ChangePasswordPresenter;
import com.soft.credit911.Changepassword.mvp.ChangePasswordView;
import com.soft.credit911.Utils.CommonUtils;
import com.soft.credit911.databinding.ActivityChangePassWordBinding;
import com.soft.credit911.databinding.ToolbarBinding;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordView {
    private ActivityChangePassWordBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    ChangePasswordPresenter changePasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityChangePassWordBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);

        changePasswordPresenter = new ChangePasswordPresenter(this, this);

        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Change Password");
        toolbarBinding.navigationIcon.setOnClickListener(v -> {
            onBackPressed();
        });

        initView();
    }

    private void initView() {
        layoutBinding.tvSave.setOnClickListener(v -> {
            if (isValid()) {
                changePasswordPresenter.changePassword(layoutBinding.etCurrentPassword.getText().toString().trim(), layoutBinding.etNewPassword.getText().toString().trim(), layoutBinding.etConfirmPassword.getText().toString().trim());
            }
        });

    }

    private boolean isValid() {
        if (layoutBinding.etConfirmPassword.getText().toString().trim().equals("")) {
            layoutBinding.etConfirmPassword.setError(getResources().getString(R.string.currentPassword_error));
            layoutBinding.etConfirmPassword.requestFocus();
            return false;
        }
        if (layoutBinding.etNewPassword.getText().toString().trim().equals("")) {
            layoutBinding.etNewPassword.setError(getResources().getString(R.string.newPassword_error));
            layoutBinding.etNewPassword.requestFocus();
            return false;
        }
        if (layoutBinding.etConfirmPassword.getText().toString().trim().equals("")) {
            layoutBinding.etConfirmPassword.setError(getResources().getString(R.string.new_Confirm_Password_error));
            layoutBinding.etConfirmPassword.requestFocus();
            return false;
        }
    /*    if (!layoutBinding.etNewPassword.equals(layoutBinding.etConfirmPassword)) {
            layoutBinding.etConfirmPassword.setError(getResources().getString(R.string.password_not_matching));
            layoutBinding.etConfirmPassword.requestFocus();
            return false;
        }*/

        return true;
    }

    @Override
    public void ChangePasswordResponse(ChangePasswordResponse changePasswordResponse) {
        CommonUtils.showdialog(changePasswordResponse.getMessage(), this, false);
    }
}