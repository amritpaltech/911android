package com.soft.credit911.Login.mvp;

import com.soft.credit911.Login.Model.LoginResponse;
import com.soft.credit911.NetworkUtils.NetworkAPICallModel;

import org.json.JSONObject;

public interface LoginView {
    void LoginResponse(LoginResponse loginResponse);
}
