package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface
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

    //test
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
        extraParams: Map<String, String> = emptyMap(),  // <== for host-provided dynamic fields
        callback: RegisterCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "token" to fcmToken,
            "member_card_no" to "",
            "migrate" to "0",
            "referral_code" to referral,
            "promo" to promo.toString(),
            "email" to email,
            "pass" to password,
            "title" to title,
            "fname" to fName,
            "lname" to lName,
            "phone" to phone,
            "phone_country" to countryCode,
            "socialmediatype" to socialType,
            "socialmediatoken" to socialToken,
            "vc" to NetworkUtils.getVCKey(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "lang" to AppUtil.language,
            "os" to NetworkUtils.getOsVersion(),
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc,
            "pvc" to NetworkUtils.getHello(AppPreference.getInstance(context).getString(PrefConstant.USER_EMAIL) ?: "")
        )

        // Merge additional host-supplied params
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.signUpAPI(fields)

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
    fun getTitleList(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(), // Host can pass extra fields
        callback: TitleListCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        // Allow host app to add extra params
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getHonorificList(fields)

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



  @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getPoint(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(), // Host can pass extra fields
        callback: CommonInterface.PointsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        // Allow host app to add extra params
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getPointDetails()

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