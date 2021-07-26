package com.soft.credit911.Dashboard.mvp;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.Dashboard.Model.DashboardResponse;
import com.soft.credit911.Dashboard.fragment.DashboardFragment;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.SecurityQuestions.Model.SecurityResponse;
import com.soft.credit911.Utils.AppConstants;
import org.json.JSONObject;
import java.lang.reflect.Type;

public class DashboardPresenter implements NetworkAPIResponseCallback {
    private Context mContext;
    private DashboardView dashboardView;
    private NetworkAPICall mNetworkAPICall;
    private static final String TAG = DashboardFragment.class.getSimpleName();

    public DashboardPresenter(Context mContext, DashboardView dashboardView) {
        this.mContext = mContext;
        this.dashboardView = dashboardView;
        this.mNetworkAPICall = new NetworkAPICall();
    }

    public void creditReport(){
        try {
            JSONObject mJsObjParam = new JSONObject();
            Type parserType = new TypeToken<DashboardPresenter>() {
            }.getType();
            NetworkAPICallModel networkAPICallModel = new NetworkAPICallModel(APIConstants.GET_USER_INFO, AppConstants.GET_REQUEST, mJsObjParam);
            networkAPICallModel.setParserType(parserType);
            networkAPICallModel.setShowProgress(true);
            mNetworkAPICall.callApplicationWS((Activity) mContext, networkAPICallModel, this);
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
    }



    @Override
    public void onSuccessResponse(JSONObject response, NetworkAPICallModel networkAPICallModel) {
        switch (networkAPICallModel.getApiURL()) {
            case APIConstants.GET_USER_INFO:
                try {
                    if (response.has("code")){
                        Gson gson= new Gson();
                        SecurityResponse securityResponse = gson.fromJson(response.toString(),SecurityResponse.class);
                      //  SecurityResponse securityResponse = (SecurityResponse) response;
                        if (securityResponse != null) {
                            securityResponseFlow(securityResponse);
                        }
                    }else {
                        DashboardResponse dashboardResponse = (DashboardResponse) networkAPICallModel.getResponseObject();
                        if (dashboardResponse != null) {
                            dashboardResponseFlow(dashboardResponse);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void dashboardResponseFlow(DashboardResponse dashboardResponse) {
        dashboardView.DashboardResponse(dashboardResponse);
    }

    private void securityResponseFlow(SecurityResponse securityResponse) {
        dashboardView.SecurityResponse(securityResponse);
    }

    @Override
    public void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel) {

    }
}
