package com.ing.quiz.network

import com.google.gson.JsonObject

import io.reactivex.Observable
import retrofit2.http.*

interface api_services {



    @POST("api/submitAnswer")
    fun submitAnswer(@Body request: JsonObject): Observable<JsonObject>

}

