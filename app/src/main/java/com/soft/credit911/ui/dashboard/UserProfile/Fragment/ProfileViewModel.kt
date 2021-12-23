package com.soft.credit911.ui.dashboard.UserProfile.Fragment

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ing.quiz.network.RestClient
import com.soft.credit911.base_classes.BaseViewModel
import com.soft.credit911.datamodel.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

class ProfileViewModel: BaseViewModel() {
    val updateResponse= MutableLiveData<UpdateProfileResponse>()
    val updateResponse2= MutableLiveData<UpdateProfileResponse>()
    val updateProfileResponse= MutableLiveData<MyProfileResponse>()

    fun uploadProfileScreen(image:String){
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                val response = webService.uploadImage(image)
                response?.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        updateResponse.postValue(it)
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


    fun updateUserInfo(userInfo: JsonObject){
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                    val response = webService.updateProfile(userInfo)
                    response?.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            updateProfileResponse.postValue(it)
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


    fun uploadProfileSignature(image:String){
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                    val response = webService.uploadSignature(image)
                    response?.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            updateResponse2.postValue(it)
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