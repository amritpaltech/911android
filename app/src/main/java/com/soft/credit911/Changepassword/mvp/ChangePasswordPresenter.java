package com.soft.credit911.Changepassword.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.Changepassword.Activity.ChangePasswordActivity;
import com.soft.credit911.Changepassword.Model.ChangePasswordResponse;
import com.soft.credit911.Utils.AppConstants;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class ChangePasswordPresenter implements NetworkAPIResponseCallback {
    private Context mContext;
    private ChangePasswordView changePasswordView;
    private NetworkAPICall mNetworkAPICall;

    private static final String TAG = ChangePasswordActivity.class.getSimpleName();

    public ChangePasswordPresenter(Context mContext, ChangePasswordView changePasswordView) {
        this.mContext = mContext;
        this.changePasswordView = changePasswordView;
        this.mNetworkAPICall = new NetworkAPICall();
    }

    public void changePassword(String currentPassword, String newPassword, String newConfirmPassword) {

        try {
            JSONObject mJsObjParam = new JSONObject();
            mJsObjParam.put("current_password ", currentPassword);
            mJsObjParam.put("new_password ", newPassword);
            mJsObjParam.put("new_confirm_password ", newConfirmPassword);
            Type parserType = new TypeToken<ChangePasswordResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.CHANGE_PASSWORD_API, AppConstants.POST_REQUEST, mJsObjParam);
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
            case APIConstants.CHANGE_PASSWORD_API:
                try {
                    ChangePasswordResponse changePasswordResponse = (ChangePasswordResponse) networkAPICallModel.getResponseObject();
                    if (changePasswordResponse != null) {
                        changePasswordResponse(changePasswordResponse);
                    }

                } catch (Exception e) {
                   
                }
                break;
            default:
                break;
        }
    }

    private void changePasswordResponse(ChangePasswordResponse changePasswordResponse) {
        changePasswordView.ChangePasswordResponse(changePasswordResponse);
    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }
}
