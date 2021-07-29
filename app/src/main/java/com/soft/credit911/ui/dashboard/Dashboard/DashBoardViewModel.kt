package com.soft.credit911.ui.dashboard.Dashboard

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ing.quiz.network.RestClient
import com.soft.credit911.base_classes.BaseViewModel
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.datamodel.LoginResponse
import com.soft.credit911.datamodel.SecurityResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

class DashBoardViewModel: BaseViewModel() {
    val responseAppHomedata= MutableLiveData<DashboardResponse>()
    val responseSecurity= MutableLiveData<SecurityResponse>()

    fun getCreditInfo(){
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                val response = webService.getUserReportData()
                response?.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val obj= JSONObject(it.toString())
                        if(obj.has("code")){
                            responseSecurity.postValue(Gson().fromJson(obj.toString(),SecurityResponse::class.java))
                        }else{
                            responseAppHomedata.postValue(Gson().fromJson(obj.toString(),DashboardResponse::class.java))
                        }

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