package com.soft.credit911.NetworkUtils;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.ui.SecurityQuestions.Activity.ErrorActivity;
import com.soft.credit911.ui.SecurityQuestions.Model.SecurityResponse;
import com.soft.credit911.Utils.AppPreference;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class NetworkAPICall {

    public void callApplicationWS(Activity mContext,
                                  NetworkAPICallModel networkAPICallModel,
                                  NetworkAPIResponseCallback networkAPIResponseCallback) {
        try {
            if (networkAPICallModel.isShowProgress())
                networkAPICallModel.getCommonUtils().showProgress(mContext);
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            StringRequest stringRequest = new StringRequest(networkAPICallModel.getRequest_type(), APIConstants.BASE_URL + networkAPICallModel.getApiURL(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        networkAPICallModel.getCommonUtils().hideProgress();
                        if (networkAPICallModel.getParserType() != null) {
                            Object object = new Gson().fromJson(response.toString(), networkAPICallModel.getParserType());
                            networkAPICallModel.setResponseObject(object);
                        }
                        if (networkAPIResponseCallback != null)
                            networkAPIResponseCallback.onSuccessResponse(new JSONObject(response), networkAPICallModel);
                    } catch (Exception jsonException) {
                        try {
                            networkAPICallModel.getCommonUtils().hideProgress();
                            if (networkAPICallModel.getParserType() != null) {
                                Object object = new Gson().fromJson(response.toString(), new TypeToken<SecurityResponse>() {
                                }.getType());
                                networkAPICallModel.setResponseObject(object);
                            }
                            if (networkAPIResponseCallback != null) {
                                networkAPIResponseCallback.onSuccessResponse(new JSONObject(response), networkAPICallModel);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        jsonException.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    networkAPICallModel.getCommonUtils().hideProgress();
                    NetworkResponse response = error.networkResponse;
                    if (error instanceof ServerError && response != null) {
                        try {
                            String json = new String(response.data);
                            JSONObject object = new JSONObject(json);
                            Intent intent = new Intent(mContext, ErrorActivity.class);
                            intent.putExtra("message",object.getString("message"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            mContext.startActivity(intent);
//                            CommonUtils.showdialog(object.getString("message"), mContext, false);
                        } catch (Exception e1) {
                            // Couldn't properly decode data to string
                            e1.printStackTrace();
                        }
                    }
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Type type = new TypeToken<HashMap<String, String>>() {
                    }.getType();
                    params = new Gson().fromJson(networkAPICallModel.getJsonObjectRequest().toString(), type);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    if (!new AppPreference(mContext).getUserObjectString().equals("")) {
                        String api_token = new AppPreference(mContext).getUserObject().getData().getApiToken();
                        headers.put("Authorization", "Bearer " + api_token);
                    }
                    return headers;
                }
            };
            requestQueue.add(stringRequest);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}