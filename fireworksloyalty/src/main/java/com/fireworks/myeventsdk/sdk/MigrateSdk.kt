package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.CommonInterface.MigrateUserCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.MigratedDataCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.PrefConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MigrateSdk {


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
    fun checkMigrateUser(
        context: Context,
        email: String,
        phone: String,
        countryCode: String,
        callback: MigrateUserCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.checkMigratedUser(
                    vc = NetworkUtils.getVCKey(),
                    date = NetworkUtils.unixTimeStamp().toString(),
                    type = "login",
                    email = email,
                    phone = countryCode + phone,
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed with code: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getMigratedData(
        context: Context,
        cardNo: String,
        idType: Int,
        nric: String,
        countryCode: String,
        phone: String,
        callback: MigratedDataCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getMigratedUserData(
                    vc = NetworkUtils.getVCKey(),
                    date = NetworkUtils.unixTimeStamp().toString(),
                    cardNo = cardNo,
                    idType = idType,
                    nric = nric,
                    phoneCountry = countryCode,
                    phone = phone,
                    sv = Constants.svc,
                    pvc = NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed with code: ${response.code()}")
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