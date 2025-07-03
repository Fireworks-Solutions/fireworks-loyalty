package com.fireworks.myeventsdk.sdk

import android.content.Context
import android.database.Observable
import android.util.Log
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.NetworkUtils.deviceId
import com.fireworks.myeventsdk.model.login.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AuthenticateSdk {

    interface LoginCallback {
        fun onSuccess(response: LoginResponse)
        fun onFailure(errorMessage: String)
    }

    private lateinit var retrofitService: Service

    fun init(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        AuthenticateSdk.retrofitService = retrofit.create(Service::class.java)
    }

    fun login(
        context: Context,
        callback: LoginCallback
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.loginAPI(
                    email = "mani@fireworks.my",
                    phone = "",
                    phoneCountry = "",
                    nric = "",
                    password = "Fireworks@1234",
                    socialType = "",
                    socialToken = "",
                    merchantId = "44",
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = "Android",
                    phoneType = "Android",
                    lang = "en",
                    deviceId = deviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    svc = Constants.svc,
                    pvc = NetworkUtils.getHello("mani@fireworks.my")
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)

                        Log.d("AuthenticateSdk", "Login successful: ${response.body()}")
                    } else {
                        callback.onFailure("Login failed with status: ${response.code()}")
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }
    }


}
