package com.soft.credit911.ui.documnet

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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

class DocumentViewModel: BaseViewModel() {
    val dataDocs= MutableLiveData<data_docs>()


    fun getDocuments(){
        doAsync{
            GlobalScope.launch(Dispatchers.IO) {
                try{
                    isLoading.postValue(true)
                    val webService = RestClient.create()
                val response = webService.getDocumentList()
                response?.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                            dataDocs.postValue(it)
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


    fun uploadDocument(partMap: Map<String, @JvmSuppressWildcards RequestBody>,
                       file: ArrayList<MultipartBody.Part>) {
        isLoading.value = true
        doAsync {
            GlobalScope.launch(Dispatchers.IO) {
                val webService = RestClient.create()
                try {
                    val response =
                        webService.updateDocument(partMap,file)
                    response?.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe({
                            isLoading.value=false
                            dataDocs.postValue(it)
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
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
        }
    }

}