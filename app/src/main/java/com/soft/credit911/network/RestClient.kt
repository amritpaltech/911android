package com.ing.quiz.network


import android.os.Handler
import com.google.gson.Gson
import com.ing.quiz.shared_prefrences.Prefs
import com.ing.quiz.shared_prefrences.SharedPreferencesName
import com.ing.quiz.ui.base_classes.BaseActivity
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.soft.credit911.NetworkUtils.APIConstants
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {


    companion object {
        val MULTIPART_FORM_DATA = "multipart/form-data"
        fun create(): api_services {
            var interceptor = HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            val clientBuilder = OkHttpClient.Builder()
            clientBuilder.addInterceptor(interceptor)
                .build()
            clientBuilder.writeTimeout(1, TimeUnit.MINUTES)
            clientBuilder.readTimeout(1, TimeUnit.MINUTES)

            val retrofit2 = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory
                        .create()
                )
                .client(clientBuilder.build())
                .baseUrl(APIConstants.BASE_URL)
                .build()
            return retrofit2.create(api_services::class.java)

        }
    }

}