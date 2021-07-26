package com.ing.quiz.network

import com.google.gson.JsonObject
import com.soft.credit911.datamodel.LoginResponse

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.util.*
interface api_services {




    @POST("api/customer/sign-in")
    fun LoginUser(
        @Body login:JsonObject
    ): Observable<LoginResponse>


}

