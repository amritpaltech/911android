package com.soft.credit911.UserProfile.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.Login.LoginActivity;
import com.soft.credit911.Login.Model.LoginResponse;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.UserProfile.Fragment.UserProfileFragment;
import com.soft.credit911.UserProfile.Model.UpdateProfileResponse;
import com.soft.credit911.Utils.AppConstants;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class UpdateProfilePresenter implements NetworkAPIResponseCallback {
    private Context mContext;
    private UpdateProfileView updateProfileView;
    private NetworkAPICall mNetworkAPICall;
    private static final String TAG = UserProfileFragment.class.getSimpleName();

    public UpdateProfilePresenter(Context mContext, UpdateProfileView updateProfileView) {
        this.mContext = mContext;
        this.updateProfileView = updateProfileView;
        this.mNetworkAPICall = new NetworkAPICall();
    }

    public void updateProfileAvatar() {
        try {
            JSONObject mJsObjParam = new JSONObject();

            Type parserType = new TypeToken<UpdateProfileResponse>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.UPDATE_PROFILE_AVATAR, AppConstants.POST_REQUEST, mJsObjParam);
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
            case APIConstants.UPDATE_PROFILE_AVATAR:
                try {
                    UpdateProfileResponse updateProfileResponse = (UpdateProfileResponse) networkAPICallModel.getResponseObject();
                    if (updateProfileResponse != null) {
                        updateProFileResponseFlow(updateProfileResponse);
                    }
                } catch (Exception e) {
                    // mCommonUtils.printStackTrace(e);
                }
                break;
            default:
                break;
        }

    }

    private void updateProFileResponseFlow(UpdateProfileResponse updateProfileResponse) {
        updateProfileView.UpdateProfileResponse(updateProfileResponse);
    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }
}
