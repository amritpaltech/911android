package com.soft.credit911.MyProfile.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.MyProfile.Activity.MyProfileActivity;
import com.soft.credit911.MyProfile.Model.MyProfileResponse;
import com.soft.credit911.Utils.AppConstants;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class MyProfilePresenter implements NetworkAPIResponseCallback {
    private Context mContext;
    private MyProfileView myProfileView;
    private NetworkAPICall mNetworkAPICall;
    private static final String TAG = MyProfileActivity.class.getSimpleName();

    public MyProfilePresenter(Context mContext, MyProfileView myProfileView) {
        this.mContext = mContext;
        this.myProfileView = myProfileView;
        this.mNetworkAPICall = new NetworkAPICall();
    }

    public void myProfile(String firstName, String lastName, String phoneNumber) {
        try {
            JSONObject mJsObjParam = new JSONObject();
            mJsObjParam.put("first_name", firstName);
            mJsObjParam.put("last_name", lastName);
            mJsObjParam.put("phone_number", phoneNumber);
            Type parserType = new TypeToken<MyProfileResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.UPDATE_PROFILE, AppConstants.POST_REQUEST, mJsObjParam);
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
            case APIConstants.UPDATE_PROFILE:
                try {
                    MyProfileResponse myProfileResponse = (MyProfileResponse) networkAPICallModel.getResponseObject();
                    if (myProfileResponse != null) {
                        myProfileResponseFlow(myProfileResponse);
                    }

                } catch (Exception e) {

                }
                break;
            default:
                break;
        }


    }

    private void myProfileResponseFlow(MyProfileResponse myProfileResponse) {
        myProfileView.MyProfileResponse(myProfileResponse);
    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }
}
