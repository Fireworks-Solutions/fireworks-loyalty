package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.VersioningCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object SplashSdk {

    private lateinit var retrofitService: Service
    private lateinit var appPreference: AppPreference

    fun init(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(Service::class.java)
    }


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun versioningApi(
        context: Context,
        customerId: String,
        appversion: String,
        deviceflavour: String,
        extraParams: Map<String, String> = emptyMap(), // <-- host can send more
        callback: VersioningCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "vc" to NetworkUtils.getVCKey(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "custid" to customerId,
            "lang" to AppUtil.language,
            "os" to NetworkUtils.getOsVersion(),
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceName(context),
            "appversion" to appversion,
            "deviceflavour" to deviceflavour,
            "svc" to Constants.svc
        )

        // Add extra dynamic fields from host app
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.versioningAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Error: ${response.code()}")
                    }
                }

            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("IO Exception: ${e.localizedMessage}")
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("HTTP Exception: ${e.localizedMessage}")
                }
            }
        }
    }



}