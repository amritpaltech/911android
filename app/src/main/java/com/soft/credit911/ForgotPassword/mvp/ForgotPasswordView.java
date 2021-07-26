package com.soft.credit911.ForgotPassword.mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.ForgotPassword.model.ForgotPasswordResponse;
import com.soft.credit911.Login.LoginActivity;
import com.soft.credit911.Login.Model.LoginResponse;
import com.soft.credit911.Login.mvp.LoginView;
import com.soft.credit911.NetworkUtils.APIConstants;
import com.soft.credit911.NetworkUtils.NetworkAPICall;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback;
import com.soft.credit911.Utils.AppConstants;
import com.soft.credit911.Utils.CommonUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;

public interface ForgotPasswordView {
    void forgotPasswordResponse(ForgotPasswordResponse forgotPasswordResponse);
}
