package com.soft.credit911.ui.OTPVerification

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.ing.quiz.network.RestClient
import com.soft.credit911.NetworkUtils.APIConstants
import com.soft.credit911.NetworkUtils.NetworkAPICallModel
import com.soft.credit911.Utils.AppConstants
import com.soft.credit911.base_classes.BaseViewModel
import com.soft.credit911.datamodel.GenerateOTPResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.SocketTimeoutException

class OtpViewModel: BaseViewModel() {
    val getOtpResponse= MutableLiveData<GenerateOTPResponse>()
    val otpVeryResponse= MutableLiveData<GenerateOTPResponse>()

    fun getOTPVerification() {
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                    val response = webService.generate2fatoken()
                    response?.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            getOtpResponse.postValue(it)
                            isLoading.postValue(false)
                        }, { error ->
                            isLoading.postValue(false)
                            if (error is SocketTimeoutException)
                            {
                                "No Internet Connection try again later"?.let { apiError.postValue(it) }
                            } else if(error is com.jakewharton.retrofit2.adapter.rxjava2.HttpException){

                                var body=(error as com.jakewharton.retrofit2.adapter.rxjava2.HttpException).response().errorBody()?.string()
                                var obj=JSONObject(body)
                                apiError.postValue(obj.getString("message"))
                            }
                            else {
                                apiError.postValue("Something Went Wrong")
                            }

                        })
                }catch (e:Exception){
                    e.printStackTrace()

                }            }
        }
    }

    fun verifyOtp(code:String) {
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                    var mJsObjParam=JSONObject()
                    mJsObjParam.put("code", code)
                    val myOb = JsonParser().parse(mJsObjParam.toString()).asJsonObject
                    val response = webService.verifyOtp(myOb)
                    response?.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            otpVeryResponse.postValue(it)
                            isLoading.postValue(false)
                        }, { error ->
                            isLoading.postValue(false)
                            if (error is SocketTimeoutException)
                            {
                                "No Internet Connection try again later"?.let { apiError.postValue(it) }
                            } else if(error is com.jakewharton.retrofit2.adapter.rxjava2.HttpException){

                                var body=(error as com.jakewharton.retrofit2.adapter.rxjava2.HttpException).response().errorBody()?.string()
                                var obj=JSONObject(body)
                                apiError.postValue(obj.getString("message"))
                            }
                            else {
                                apiError.postValue("Something Went Wrong")
                            }

                        })
                }catch (e:Exception){
                    e.printStackTrace()

                }            }
        }
    }
    }

