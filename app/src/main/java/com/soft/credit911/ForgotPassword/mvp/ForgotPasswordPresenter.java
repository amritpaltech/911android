package com.soft.credit911.ForgotPassword.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.ForgotPassword.model.ForgotPasswordResponse;
import com.soft.credit911.Login.LoginActivity;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.Utils.AppConstants;
import com.soft.credit911.Utils.CommonUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class ForgotPasswordPresenter implements NetworkAPIResponseCallback {
    private Context mContext;
    private ForgotPasswordView forgotPasswordView;
    private CommonUtils mCommonUtils;
    private NetworkAPICall mNetworkAPICall;
    private static final String TAG = LoginActivity.class.getSimpleName();

    public ForgotPasswordPresenter(Context mContext, ForgotPasswordView forgotPasswordView) {
        this.mContext = mContext;
        this.forgotPasswordView = forgotPasswordView;
        mCommonUtils = new CommonUtils();
        mNetworkAPICall = new NetworkAPICall();
    }

    public void forgotPassword(String email) {
        try {
            JSONObject mJsObjParam = new JSONObject();
            mJsObjParam.put("email", email);
            Type parserType = new TypeToken<ForgotPasswordResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.RESET_PASSWORD_API, AppConstants.POST_REQUEST, mJsObjParam);
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
            case APIConstants.RESET_PASSWORD_API:
                try {
                    ForgotPasswordResponse forgotPasswordResponse = (ForgotPasswordResponse) networkAPICallModel.getResponseObject();
                    if (forgotPasswordResponse != null) {
                        forgotPasswordResponseFlow(forgotPasswordResponse);
                    }
                } catch (Exception e) {
                    // mCommonUtils.printStackTrace(e);
                }
                break;
            default:
                break;
        }
    }

    private void forgotPasswordResponseFlow(ForgotPasswordResponse forgotPasswordResponse) {
       forgotPasswordView.forgotPasswordResponse(forgotPasswordResponse);
    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }
}