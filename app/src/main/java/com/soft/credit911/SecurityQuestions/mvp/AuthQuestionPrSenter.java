package com.soft.credit911.SecurityQuestions.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.SecurityQuestions.Activity.SecurityQuestionsActivity;
import com.soft.credit911.SecurityQuestions.Model.AuthQuestionResponse;
import com.soft.credit911.SecurityQuestions.Model.SecurityResponse;
import com.soft.credit911.Utils.AppConstants;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class AuthQuestionPrSenter implements NetworkAPIResponseCallback {
    private Context mContext;
    private AuthQuestionView authQuestionView;
    private NetworkAPICall mNetworkAPICall;
    private static final String TAG = SecurityQuestionsActivity.class.getSimpleName();

    public AuthQuestionPrSenter(Context mContext, AuthQuestionView authQuestionView) {
        this.mContext = mContext;
        this.authQuestionView = authQuestionView;
        this.mNetworkAPICall = new NetworkAPICall();
    }
    public void authQuestion(String q1, String a1, String q2, String a2, String q3, String a3, String token) {
        String que1 = "answer["+q1+"]";
        String que2 = "answer["+q2+"]";
        String que3 = "answer["+q3+"]";
        try {
            JSONObject mJsObjParam = new JSONObject();
            mJsObjParam.put(que1,a1);
            mJsObjParam.put(que2,a2);
            mJsObjParam.put(que3,a3);
            mJsObjParam.put("auth-token",token);
            Type parserType = new TypeToken<AuthQuestionResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.Auth_Question, AppConstants.POST_REQUEST, mJsObjParam);
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
            case APIConstants.Auth_Question:
                try {
                    AuthQuestionResponse authQuestionResponse = (AuthQuestionResponse) networkAPICallModel.getResponseObject();
                    if (authQuestionResponse != null) {
                        authQuestionResponseFlow(authQuestionResponse);
                    }
                } catch (Exception e) {
                    // mCommonUtils.printStackTrace(e);
                }
                break;
            default:
                break;
        }
    }

    private void authQuestionResponseFlow(AuthQuestionResponse authQuestionResponse) {
        authQuestionView.AuthQuestionResponse(authQuestionResponse);
    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }
}
