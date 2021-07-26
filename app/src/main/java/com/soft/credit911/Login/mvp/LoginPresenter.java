package com.soft.credit911.Login.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.Login.LoginActivity;
import com.soft.credit911.Login.Model.LoginResponse;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.Utils.AppConstants;
import com.soft.credit911.Utils.CommonUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class LoginPresenter implements NetworkAPIResponseCallback {
    private Context mContext;
    private LoginView loginView;
    private CommonUtils mCommonUtils;
    private NetworkAPICall mNetworkAPICall;
    private static final String TAG = LoginActivity.class.getSimpleName();

    public LoginPresenter(Context mContext, LoginView loginView) {
        this.mContext = mContext;
        this.loginView = loginView;
        mCommonUtils = new CommonUtils();
        mNetworkAPICall = new NetworkAPICall();
    }

    public void signIn(String email, String password){
        try {
            JSONObject mJsObjParam = new JSONObject();
            mJsObjParam.put("email",email);
            mJsObjParam.put("password",password);
            Type parserType = new TypeToken<LoginResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.LOGIN_API, AppConstants.POST_REQUEST, mJsObjParam);
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
            case APIConstants.LOGIN_API:
                try {
                    LoginResponse loginResponse = (LoginResponse) networkAPICallModel.getResponseObject();
                    if(loginResponse != null){
                        loginResponseFlow(loginResponse);
                    }
                } catch (Exception e) {
                   // mCommonUtils.printStackTrace(e);
                }
                break;
            default:
                break;
        }
    }

    private void loginResponseFlow(LoginResponse loginResponse) {
        loginView.LoginResponse(loginResponse);
    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }
}
