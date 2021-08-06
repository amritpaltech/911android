package com.soft.credit911.ui.casemanagement.Fragement

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ing.quiz.network.RestClient
import com.soft.credit911.base_classes.BaseViewModel
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.datamodel.LoginResponse
import com.soft.credit911.datamodel.SecurityResponse
import com.soft.credit911.datamodel.data_cases
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

class CaseViewModel: BaseViewModel() {
    val dataCases= MutableLiveData<data_cases>()


    fun getCases(){
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                val response = webService.getCases()
                response?.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                            dataCases.postValue(it)
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