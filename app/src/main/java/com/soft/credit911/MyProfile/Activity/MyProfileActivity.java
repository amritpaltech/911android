package com.soft.credit911.MyProfile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.soft.credit911.R;
import com.soft.credit911.MyProfile.Model.MyProfileResponse;
import com.soft.credit911.MyProfile.mvp.MyProfilePresenter;
import com.soft.credit911.MyProfile.mvp.MyProfileView;
import com.soft.credit911.Utils.CommonUtils;
import com.soft.credit911.databinding.ActivityMyProfileBinding;
import com.soft.credit911.databinding.ToolbarBinding;

public class MyProfileActivity extends AppCompatActivity implements MyProfileView {
    private ActivityMyProfileBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    MyProfilePresenter myProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityMyProfileBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);

        myProfilePresenter = new MyProfilePresenter(this, (MyProfileView) this);
        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("MyProfile");
        toolbarBinding.navigationIcon.setOnClickListener(v -> {
            onBackPressed();
        });
        initView();
    }

    private void initView() {
        layoutBinding.tvSave.setOnClickListener(v -> {
            if (isValid()) {
                myProfilePresenter.myProfile(layoutBinding.etFirstName.getText().toString().trim(), layoutBinding.etLastName.getText().toString().trim(), layoutBinding.etPhone.getText().toString().trim());
            }
        });
    }

    private boolean isValid() {
        if (layoutBinding.etFirstName.getText().toString().trim().equals("")) {
            layoutBinding.etFirstName.setError(getResources().getString(R.string.first_name_error));
            layoutBinding.etFirstName.requestFocus();
            return false;
        }

        if (layoutBinding.etLastName.getText().toString().trim().equals("")) {
            layoutBinding.etLastName.setError(getResources().getString(R.string.last_name_error));
            layoutBinding.etLastName.requestFocus();
            return false;
        }
        if (layoutBinding.etPhone.getText().toString().trim().equals("")) {
            layoutBinding.etPhone.setError(getResources().getString(R.string.phone_number_error));
            layoutBinding.etPhone.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void MyProfileResponse(MyProfileResponse myProfileResponse) {
        CommonUtils.showdialog(myProfileResponse.getMessage(), this, false);
    }
}