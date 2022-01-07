package com.ing.quiz.network

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat.getSystemService
import com.ing.quiz.shared_prefrences.Prefs
import com.ing.quiz.shared_prefrences.SharedPreferencesName
import com.ing.quiz.ui.base_classes.BaseActivity
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.soft.credit911.NetworkUtils.APIConstants
import com.soft.credit911.Utils.AppPreference
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


class RestClient {

    companion object {
        val MULTIPART_FORM_DATA = "multipart/form-data"

        var mBaseActivity: BaseActivity? = null
        fun createRequestBody(s: String): RequestBody {
            return RequestBody.create(
                MULTIPART_FORM_DATA.toMediaTypeOrNull(), s
            )
        }

        fun create(): api_services {
            var IMEI_NO=""

                IMEI_NO = Settings.Secure.getString(
                    mBaseActivity?.getContentResolver(),
                    Settings.Secure.ANDROID_ID
                )


            var interceptor = HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            var client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            var token = ""
            try {
                val info = AppPreference(mBaseActivity).getUserObject().data?.apiToken
                if (info != null && info.isNotEmpty()) {
                    token = "Bearer " +info ?: ""

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            val clientBuilder = OkHttpClient.Builder()
            if (token != null && token.length > 0) {
                clientBuilder.addInterceptor { chain ->
                    val request = mBaseActivity?.let { Prefs.with(it?.applicationContext).getString(SharedPreferencesName.DEVICETOKEN,"") }
                        ?.let {
                            chain.request()
                                .newBuilder()
                                .addHeader("Authorization", token)
                                .addHeader("device_id",
                                    it
                                )
                                .addHeader("device_type", "android")
                                .addHeader("device_unique_id", IMEI_NO)
                                .addHeader("device_info", Build.MANUFACTURER+Build.MODEL+","+android.os.Build.VERSION.SDK)
                                .addHeader("device_timezone", TimeZone.getDefault().id.toString())
                                .addHeader("app_version",
                                    mBaseActivity?.getPackageManager()?.getPackageInfo(mBaseActivity?.getPackageName()!!, 0)?.versionName.toString()
                                )
                                .build()
                        }
                    request?.let { chain.proceed(it) }!!
                }
            } else {
                clientBuilder.addInterceptor { chain ->
                    val request = mBaseActivity?.let { Prefs.with(it?.applicationContext).getString(SharedPreferencesName.DEVICETOKEN,"") }
                        ?.let {
                            chain.request()
                                .newBuilder()
                                .addHeader("device_id",
                                    it
                                )
                                .addHeader("device_type", "android")
                                .addHeader("app_version",
                                    mBaseActivity?.getPackageManager()?.getPackageInfo(mBaseActivity?.getPackageName()!!, 0)?.versionName.toString()
                                )
                                .addHeader("device_info", Build.MANUFACTURER+Build.MODEL+", "+android.os.Build.VERSION.SDK)
                                .addHeader("device_timezone", TimeZone.getDefault().id.toString())
                                .build()
                        }
                    request?.let { chain.proceed(it) }!!
                }
            }
            clientBuilder.writeTimeout(1, TimeUnit.MINUTES)
            clientBuilder.readTimeout(1, TimeUnit.MINUTES)

            clientBuilder.addInterceptor(interceptor).build()
            val retrofit2 = Retrofit.Builder()
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