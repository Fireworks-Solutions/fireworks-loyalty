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
        deviceflavour : String,
        callback: VersioningCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.versioningAPI(
                    vc = NetworkUtils.getVCKey(),
                    date = NetworkUtils.unixTimeStamp().toString(),
                    custId = customerId,
                    lang = AppUtil.language,
                    os = NetworkUtils.getOsVersion(),
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceName(context),
                    appVersion = appversion,
                    deviceflavour = deviceflavour,
                    sv = Constants.svc
                )

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