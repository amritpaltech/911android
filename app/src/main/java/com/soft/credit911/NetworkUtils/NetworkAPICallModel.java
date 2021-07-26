package com.soft.credit911.NetworkUtils;

import android.app.Activity;

import com.soft.credit911.Utils.CommonUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class NetworkAPICallModel {
    private String apiURL;
    private int request_type;
    private JSONObject jsonObjectRequest;
    private int timeOut = 30 * 1000;
    private Type parserType;
    private Object responseObject;
    private boolean showProgress = true;
    private Activity activity;
    private int max_try = 0;
    private int max_try_error = 0;
    private JSONObject encryptedRequest = null;
    private boolean isProgressVisible = false;
    private CommonUtils commonUtils = new CommonUtils();

    public NetworkAPICallModel(String apiURL, int request_type, JSONObject jsonObjectRequest) {
        this.apiURL = apiURL;
        this.request_type = request_type;
        this.jsonObjectRequest = jsonObjectRequest;
    }

    public CommonUtils getCommonUtils() {
        return commonUtils;
    }

    public void setCommonUtils(CommonUtils commonUtils) {
        this.commonUtils = commonUtils;
    }

    public boolean isProgressVisible() {
        return isProgressVisible;
    }

    public void setProgressVisible(boolean progressVisible) {
        isProgressVisible = progressVisible;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    public String getApiURL() {
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }

    public JSONObject getJsonObjectRequest() {
        return jsonObjectRequest;
    }

    public void setJsonObjectRequest(JSONObject jsonObjectRequest) {
        this.jsonObjectRequest = jsonObjectRequest;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public Type getParserType() {
          return parserType;
    }

    public void setParserType(Type parserType) {
        this.parserType = parserType;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

    public int getRequest_type() {
        return request_type;
    }

    public void setRequest_type(int request_type) {
        this.request_type = request_type;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public int getMax_try() {
        return max_try;
    }

    public void setMax_try(int max_try) {
        this.max_try = max_try;
    }

    public int getMax_try_error() {
        return max_try_error;
    }

    public void setMax_try_error(int max_try_error) {
        this.max_try_error = max_try_error;
    }

    public JSONObject getEncryptedRequest() {
        return encryptedRequest;
    }

    public void setEncryptedRequest(JSONObject encryptedRequest) {
        this.encryptedRequest = encryptedRequest;
    }
}
