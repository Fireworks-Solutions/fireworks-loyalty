package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.RegisterCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.TitleListCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.PrefConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RegisterSdk {


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
    fun registerUser(
        context: Context,
        fcmToken: String,
        referral: String,
        promo: Boolean,
        email: String,
        password: String,
        title: String,
        fName: String,
        lName: String,
        phone: String,
        countryCode: String,
        socialType: String,
        socialToken: String,
        callback: RegisterCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.signUpAPI(
                    fcmToken = fcmToken,
                    cardNo = "",
                    migrate = 0,
                    referral = referral,
                    promo = promo,
                    email = email,
                    password = password,
                    title = title,
                    firstName = fName,
                    lastName = lName,
                    phone = phone,
                    phoneCountry = countryCode,
                    socialType = socialType,
                    socialToken = socialToken,
                    vc = NetworkUtils.getVCKey(),
                    date = NetworkUtils.unixTimeStamp().toString(),
                    lang = AppUtil.language,
                    os = NetworkUtils.getOsVersion(),
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc,
                    pv = NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Registration failed: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Unexpected error occurred")
                }
            }
        }
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getTitleList(context: Context, callback: TitleListCallback) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getHonorificList(
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
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
                    callback.onFailure(e.localizedMessage ?: "Unexpected error occurred")
                }
            }
        }
    }


}