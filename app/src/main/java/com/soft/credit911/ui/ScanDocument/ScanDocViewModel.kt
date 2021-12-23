package com.soft.credit911.ui.Changepassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonParser
import com.ing.quiz.network.RestClient
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.base_classes.BaseViewModel
import com.soft.credit911.datamodel.ChangePasswordResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.json.JSONException
import org.json.JSONObject
import java.net.SocketTimeoutException


class ScanDocViewModel: BaseViewModel() {
    val responsePasswordChange= MutableLiveData<ChangePasswordResponse>()
    fun changePassword( newPassword:String,  newConfirmPassword:String) {
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                    val response = webService.upDateCredit(newPassword,newConfirmPassword)
                    response?.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            responsePasswordChange.postValue(it)
                            isLoading.postValue(false)
                        }, { error ->
                            isLoading.postValue(false)
                            if (error is SocketTimeoutException)
                            {
                                "No Internet Connection try again later"?.let { apiError.postValue(it) }
                            } else if(error is com.jakewharton.retrofit2.adapter.rxjava2.HttpException){
                                apiError.postValue(CommonUtils.handleServerResponses(error))
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