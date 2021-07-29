package com.ing.quiz.network

import com.google.gson.JsonObject
import com.soft.credit911.datamodel.ChangePasswordResponse
import com.soft.credit911.datamodel.LoginResponse
import com.soft.credit911.datamodel.UpdateProfileResponse

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.util.*
import kotlin.collections.HashMap

interface api_services {




    @POST("api/customer/sign-in")
    fun LoginUser(
        @Body login:JsonObject
    ): Observable<LoginResponse>

    @FormUrlEncoded
    @POST("api/change-password")
    fun changePassword(
        @Field("new_password") new_password: String,
        @Field("new_confirm_password") new_confirm_password: String
    ): Observable<ChangePasswordResponse>

    @GET("api/get-user-info")
    fun getUserReportData(): Observable<JsonObject>

    @FormUrlEncoded
    @POST("api/update-avatar")
    fun uploadImage(
        @Field("imageblob") new_password: String
    ): Observable<UpdateProfileResponse>

}

