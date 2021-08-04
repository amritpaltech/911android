package com.soft.credit911.ui.OTPVerification.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.ui.Login.LoginActivity;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.datamodel.GenerateOTPResponse;
import com.soft.credit911.Utils.AppConstants;
import com.soft.credit911.Utils.CommonUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class OTPVerificationPresenter implements NetworkAPIResponseCallback {

    private Context mContext;
    private OTPVerificationView otpVerificationView;
    private CommonUtils mCommonUtils;
    private NetworkAPICall mNetworkAPICall;
    private static final String TAG = LoginActivity.class.getSimpleName();

    public OTPVerificationPresenter(Context mContext, OTPVerificationView otpVerificationView) {
        this.mContext = mContext;
        this.otpVerificationView = otpVerificationView;
        mCommonUtils = new CommonUtils();
        mNetworkAPICall = new NetworkAPICall();
    }

    public void getOTPVerification() {
        try {
            JSONObject mJsObjParam = new JSONObject();
            Type parserType = new TypeToken<GenerateOTPResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.GENERATE_OTP_API, AppConstants.GET_REQUEST, mJsObjParam);
            networkAPICallModel.setParserType(parserType);
            networkAPICallModel.setShowProgress(true);
            mNetworkAPICall.callApplicationWS((Activity) mContext, networkAPICallModel, this);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void verifiyOTP(String code) {
        try {
            JSONObject mJsObjParam = new JSONObject();
            mJsObjParam.put("code", code);
            Type parserType = new TypeToken<GenerateOTPResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.VERIFICATION_OTP_CODE,
                    AppConstants.POST_REQUEST, mJsObjParam);
            networkAPICallModel.setParserType(parserType);
            networkAPICallModel.setShowProgress(true);
            mNetworkAPICall.callApplicationWS((Activity) mContext, networkAPICallModel, this);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


    @Override
    public void onSuccessResponse(JSONObject response, NetworkAPICallModel networkAPICallModel) {
        switch (networkAPICallModel.getApiURL()) {
            case APIConstants.GENERATE_OTP_API:
                try {
                    GenerateOTPResponse generateOTPResponse = (GenerateOTPResponse) networkAPICallModel.getResponseObject();
                    if (generateOTPResponse != null) {
                        GenerateOTPResponseFlow(generateOTPResponse);
                    }
                } catch (Exception e) {
                    // mCommonUtils.printStackTrace(e);
                }
                break;
            case APIConstants.VERIFICATION_OTP_CODE:
                try {
                    GenerateOTPResponse generateOTPResponse = (GenerateOTPResponse) networkAPICallModel.getResponseObject();
                    if (generateOTPResponse != null) {
                        otpVerificationView.verifiyOTPResponse(generateOTPResponse);
                    }
                } catch (Exception e) {
                     e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void GenerateOTPResponseFlow(GenerateOTPResponse generateOTPResponse) {
        otpVerificationView.generateOTPResponse(generateOTPResponse);
    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }

}
