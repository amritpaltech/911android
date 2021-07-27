package com.soft.credit911.ui.SecurityQuestions.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.ui.SecurityQuestions.Activity.SecurityQuestionsActivity;
import com.soft.credit911.ui.SecurityQuestions.Model.SecurityResponse;
import com.soft.credit911.Utils.AppConstants;


import org.json.JSONObject;

import java.lang.reflect.Type;

public class SecurityPresenter implements NetworkAPIResponseCallback {
    private Context mContext;
    private SecurityView securityView;
    private NetworkAPICall mNetworkAPICall;
    private static final String TAG = SecurityQuestionsActivity.class.getSimpleName();

    public SecurityPresenter(Context mContext, SecurityView securityView) {
        this.mContext = mContext;
        this.securityView = securityView;
        this.mNetworkAPICall = new NetworkAPICall();
    }

    public void securityQuestion() {
        try {
            JSONObject mJsObjParam = new JSONObject();
            Type parserType = new TypeToken<SecurityResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.GET_USER_INFO, AppConstants.GET_REQUEST, mJsObjParam);
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
            case APIConstants.GET_USER_INFO:
                try {
                    SecurityResponse securityResponse = (SecurityResponse) networkAPICallModel.getResponseObject();
                    if (securityResponse != null) {
                        securityResponseFlow(securityResponse);
                    }
                } catch (Exception e) {
                    // mCommonUtils.printStackTrace(e);
                }
                break;
            default:
                break;
        }
    }

    private void securityResponseFlow(SecurityResponse securityResponse) {
        securityView.SecurityResponse(securityResponse);

    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }
}
