package com.soft.credit911.NetworkUtils;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface NetworkAPIResponseCallback{
    void onSuccessResponse(JSONObject response, NetworkAPICallModel networkAPICallModel);
    void onFailure(VolleyError volleyError, NetworkAPICallModel networkAPICallModel);
}
