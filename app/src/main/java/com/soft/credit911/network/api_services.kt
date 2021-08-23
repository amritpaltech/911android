package com.ing.quiz.network

import com.google.gson.JsonObject
import com.soft.credit911.datamodel.*

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.util.*
import kotlin.collections.HashMap

interface api_services {


    @GET("api/generate2fatoken")
    fun generate2fatoken(): Observable<GenerateOTPResponse>

    @POST("api/verify-2fa-code")
    fun verifyOtp(  @Body login:JsonObject ): Observable<GenerateOTPResponse>

    @POST("api/customer/sign-in")
    fun LoginUser(
        @Body login:JsonObject
    ): Observable<LoginResponse>

    @POST("api/update-profile")
    fun updateProfile(
        @Body login:JsonObject
    ): Observable<MyProfileResponse>


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


    @GET("api/get-doc-list")
    fun getDocumentList(): Observable<data_docs>

    @GET("api/get-cases")
    fun getCases(): Observable<data_cases>

    @GET()
    fun getNotifications(@Url string: String): Observable<data_notification>

    @Multipart
    @POST("api/upload-document")
    fun updateDocument(
        @PartMap partMap: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part file: List<MultipartBody.Part>
    ): Observable<data_docs>

}

