package com.soft.credit911.ui.MyProfile.mvp

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.google.gson.reflect.TypeToken
import com.soft.credit911.NetworkUtils.APIConstants
import com.soft.credit911.NetworkUtils.NetworkAPICall
import com.soft.credit911.NetworkUtils.NetworkAPICallModel
import com.soft.credit911.NetworkUtils.NetworkAPIResponseCallback
import com.soft.credit911.Utils.AppConstants
import com.soft.credit911.datamodel.MyProfileResponse
import com.soft.credit911.ui.MyProfile.Activity.MyProfileActivity
import org.json.JSONObject

class MyProfilePresenter(private val mContext: Context, private val myProfileView: MyProfileView) :
    NetworkAPIResponseCallback {
    private val mNetworkAPICall: NetworkAPICall
    fun myProfile(firstName: String?, lastName: String?, phoneNumber: String?) {
        try {
            val mJsObjParam = JSONObject()
            mJsObjParam.put("first_name", firstName)
            mJsObjParam.put("last_name", lastName)
            mJsObjParam.put("phone_number", phoneNumber)
            val parserType = object : TypeToken<MyProfileResponse?>() {}.type
            val networkAPICallModel = NetworkAPICallModel(
                APIConstants.UPDATE_PROFILE,
                AppConstants.POST_REQUEST,
                mJsObjParam
            )
            networkAPICallModel.parserType = parserType
            networkAPICallModel.isShowProgress = true
            mNetworkAPICall.callApplicationWS(mContext as Activity, networkAPICallModel, this)
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
        }
    }

    override fun onSuccessResponse(response: JSONObject, networkAPICallModel: NetworkAPICallModel) {
        when (networkAPICallModel.apiURL) {
            APIConstants.UPDATE_PROFILE -> try {
                val myProfileResponse = networkAPICallModel.responseObject as MyProfileResponse
                myProfileResponse?.let { myProfileResponseFlow(it) }
            } catch (e: Exception) {
            }
            else -> {
            }
        }
    }

    private fun myProfileResponseFlow(myProfileResponse: MyProfileResponse) {
        myProfileView.MyProfileResponse(myProfileResponse)
    }

    override fun onFailure(volleyError: VolleyError, networkAPICallModel: NetworkAPICallModel) {}

    companion object {
        private val TAG = MyProfileActivity::class.java.simpleName
    }

    init {
        mNetworkAPICall = NetworkAPICall()
    }
}