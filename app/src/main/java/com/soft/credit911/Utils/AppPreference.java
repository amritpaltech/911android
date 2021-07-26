package com.soft.credit911.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.soft.credit911.datamodel.LoginResponse;

public class AppPreference {
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public AppPreference(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public LoginResponse getUserObject() {
        Gson gson = new Gson();
        return gson.fromJson(appSharedPrefs.getString("user_object", ""),LoginResponse.class);
    }

    public String getUserObjectString() {
        return appSharedPrefs.getString("user_object", "");
    }

    public void setUserObject(LoginResponse value) {
        prefsEditor.putString("user_object", new Gson().toJson(value));
        prefsEditor.commit();
    }

    public boolean getIsLogin() {
        return appSharedPrefs.getBoolean("isUserLogin", false);
    }

    public void setIsLogin(boolean value) {
        prefsEditor.putBoolean("isUserLogin", value);
        prefsEditor.commit();
    }

    public String getUserLoggedIn(){
        return appSharedPrefs.getString("user_Logged_In", "");
    }

    public void setUserLoggedIn(String userId){
        String userLoggedIn = appSharedPrefs.getString("user_Logged_In", "");
        prefsEditor.putString("user_Logged_In",userLoggedIn + "," +userId);
        prefsEditor.commit();
    }
}
