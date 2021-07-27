package com.soft.credit911.ui.OTPVerification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.soft.credit911.ui.dashboard.LandingActivity;
import com.soft.credit911.datamodel.GenerateOTPResponse;
import com.soft.credit911.ui.OTPVerification.mvp.OTPVerificationPresenter;
import com.soft.credit911.ui.OTPVerification.mvp.OTPVerificationView;
import com.soft.credit911.R;
import com.soft.credit911.Utils.AppConstants;
import com.soft.credit911.Utils.AppPreference;
import com.soft.credit911.Utils.CommonUtils;
import com.soft.credit911.databinding.ActivityLoginVerificationBinding;
import com.soft.credit911.databinding.ToolbarBinding;

public class LoginVerificationActivity extends AppCompatActivity implements OTPVerificationView {
    private ActivityLoginVerificationBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    private OTPVerificationPresenter OTPVerificationPresenter;
    AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityLoginVerificationBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);
        OTPVerificationPresenter = new OTPVerificationPresenter(this, this);
        appPreference = new AppPreference(this);
        initView();
    }

    private void initView() {
        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Login");
        toolbarBinding.navigationIcon.setOnClickListener(v -> {
            onBackPressed();
        });
        OTPVerificationPresenter.getOTPVerification();
        layoutBinding.displayTextTv.setText(getResources().getString(R.string.verification_number) + " " + appPreference.getUserObject().getData().getPhoneNumber());
        layoutBinding.etOtpNum1.addTextChangedListener(new GenericTextWatcher(layoutBinding.etOtpNum1));
        layoutBinding.etOtpNum2.addTextChangedListener(new GenericTextWatcher(layoutBinding.etOtpNum2));
        layoutBinding.etOtpNum3.addTextChangedListener(new GenericTextWatcher(layoutBinding.etOtpNum3));
        layoutBinding.etOtpNum4.addTextChangedListener(new GenericTextWatcher(layoutBinding.etOtpNum4));
        layoutBinding.etOtpNum5.addTextChangedListener(new GenericTextWatcher(layoutBinding.etOtpNum5));
        layoutBinding.etOtpNum6.addTextChangedListener(new GenericTextWatcher(layoutBinding.etOtpNum6));
        layoutBinding.resendOTPTv.setOnClickListener(v -> {
            OTPVerificationPresenter.getOTPVerification();
        });
        /*toolbarBinding.tvToolbarLogin.setVisibility(View.VISIBLE);*/
        layoutBinding.tvVerify.setOnClickListener(v -> {
            if (isValid()){
                String otp = layoutBinding.etOtpNum1.getText().toString().trim()+
                        layoutBinding.etOtpNum2.getText().toString().trim()+
                        layoutBinding.etOtpNum3.getText().toString().trim()+
                        layoutBinding.etOtpNum4.getText().toString().trim()+
                        layoutBinding.etOtpNum5.getText().toString().trim()+
                        layoutBinding.etOtpNum6.getText().toString().trim();
                OTPVerificationPresenter.verifiyOTP(otp);
            }
        });

    }

    private boolean isValid() {
        if (layoutBinding.etOtpNum1.getText().toString().trim().equals("")) {
            layoutBinding.etOtpNum1.setError(getResources().getString(R.string.otp_error));
            layoutBinding.etOtpNum1.requestFocus();
            return false;
        }
        if (layoutBinding.etOtpNum2.getText().toString().trim().equals("")) {
            layoutBinding.etOtpNum2.setError(getResources().getString(R.string.otp_error));
            layoutBinding.etOtpNum2.requestFocus();
            return false;
        }
        if (layoutBinding.etOtpNum3.getText().toString().trim().equals("")) {
            layoutBinding.etOtpNum3.setError(getResources().getString(R.string.otp_error));
            layoutBinding.etOtpNum3.requestFocus();
            return false;
        }
        if (layoutBinding.etOtpNum4.getText().toString().trim().equals("")) {
            layoutBinding.etOtpNum4.setError(getResources().getString(R.string.otp_error));
            layoutBinding.etOtpNum4.requestFocus();
            return false;
        }
        if (layoutBinding.etOtpNum5.getText().toString().trim().equals("")) {
            layoutBinding.etOtpNum5.setError(getResources().getString(R.string.otp_error));
            layoutBinding.etOtpNum5.requestFocus();
            return false;
        }
        if (layoutBinding.etOtpNum6.getText().toString().trim().equals("")) {
            layoutBinding.etOtpNum6.setError(getResources().getString(R.string.otp_error));
            layoutBinding.etOtpNum6.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void generateOTPResponse(GenerateOTPResponse generateOTPResponse) {
        Toast.makeText(this, generateOTPResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void verifiyOTPResponse(GenerateOTPResponse generateOTPResponse) {
        if (generateOTPResponse.getStatus().equals(AppConstants.API_SUCCESS)) {
            appPreference.setIsLogin(true);
            if(!appPreference.getUserObjectString().equals(""))
                appPreference.setUserLoggedIn(String.valueOf(appPreference.getUserObject().getData().getId()));
            Toast.makeText(this, generateOTPResponse.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LandingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else{
            //Toast.makeText(this, generateOTPResponse.getMessage(), Toast.LENGTH_SHORT).show();
            CommonUtils.showdialog(generateOTPResponse.getMessage(), this, false);
        }
    }

    class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.et_otpNum1:
                    if (text.length() == 1)
                        layoutBinding.etOtpNum2.requestFocus();
                    break;
                case R.id.et_otpNum2:
                    if (text.length() == 1)
                        layoutBinding.etOtpNum3.requestFocus();
                    else if (text.length() == 0)
                        layoutBinding.etOtpNum1.requestFocus();
                    break;
                case R.id.et_otpNum3:
                    if (text.length() == 1)
                        layoutBinding.etOtpNum4.requestFocus();
                    else if (text.length() == 0)
                        layoutBinding.etOtpNum2.requestFocus();
                    break;
                case R.id.et_otpNum4:
                    if (text.length() == 1)
                        layoutBinding.etOtpNum5.requestFocus();
                    else if (text.length() == 0)
                        layoutBinding.etOtpNum3.requestFocus();
                    break;
                case R.id.et_otpNum5:
                    if (text.length() == 1)
                        layoutBinding.etOtpNum6.requestFocus();
                    else if (text.length() == 0)
                        layoutBinding.etOtpNum4.requestFocus();
                    break;
                case R.id.et_otpNum6:
                    if (text.length() == 0)
                        layoutBinding.etOtpNum5.requestFocus();
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }
    }
}