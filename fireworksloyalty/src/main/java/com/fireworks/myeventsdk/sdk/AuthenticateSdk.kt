package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import android.database.Observable
import android.util.Log
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.LoginCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.OtpCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.NetworkUtils.deviceId
import com.fireworks.myeventsdk.Utils.PrefConstant
import com.fireworks.myeventsdk.model.login.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

//test
object AuthenticateSdk {



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
    fun login(
        context: Context,
        email: String,
        password: String,
        extraParams: Map<String, String> = emptyMap(), // <-- optional dynamic fields
        callback: LoginCallback
    ) {
        appPreference = AppPreference.getInstance(context)

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val fields = mutableMapOf(
                    "email" to email,
                    "phone" to "",
                    "phone_country" to "",
                    "nric" to "",
                    "password" to password,
                    "socialmediatype" to "",
                    "socialmediatoken" to "",
                    "mercid" to "44",
                    "date" to NetworkUtils.unixTimeStamp().toString(),
                    "vc" to NetworkUtils.getVCKey(),
                    "os" to NetworkUtils.getOsVersion(),
                    "phonename" to NetworkUtils.getDeviceName(context),
                    "phonetype" to NetworkUtils.getDeviceLayoutType(context),
                    "lang" to AppUtil.language,
                    "deviceid" to AppUtil.getDeviceId(context),
                    "devicetype" to NetworkUtils.getDeviceLayoutType(context),
                    "svc" to Constants.svc,
                    "pvc" to NetworkUtils.getHello(email)
                )

                // Merge any extra fields passed from host app
                fields.putAll(extraParams)

                val response = retrofitService.loginAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        val loginResponse = response.body()!!

                        appPreference.putBoolean(PrefConstant.CHANGE_PW, loginResponse.changePassword?.toBoolean() ?: false)
                        appPreference.putBoolean(PrefConstant.REGISTER, loginResponse.register?.toBoolean() ?: false)
                        appPreference.putString(PrefConstant.URL, loginResponse.url ?: "")

                        if (loginResponse.sid != null) {
                            appPreference.putBoolean(PrefConstant.LOGGED_IN_STATUS, true)
                            appPreference.putString(PrefConstant.USER_EMAIL, loginResponse.email)
                            appPreference.putString(PrefConstant.USER_PHONE, loginResponse.phone)
                            appPreference.putString(PrefConstant.CUSTOMER_ID, loginResponse.custid)
                            appPreference.putString(PrefConstant.MERCHANT_ID, "44")
                            appPreference.putString(PrefConstant.SID, loginResponse.sid)

                            appPreference.putString(PrefConstant.USER_FNAME, loginResponse.fname)
                            appPreference.putString(PrefConstant.USER_LNAME, loginResponse.lname)
                            appPreference.putString(PrefConstant.USER_TOKEN, loginResponse.token)
                            appPreference.putBoolean(PrefConstant.REMEMBER_ME, true)

//                            AppUtil.applicationToken = loginResponse.token ?: ""
                        }

                        Log.d("AuthenticateSdk", "Login successful: $loginResponse")
                        callback.onSuccess(loginResponse)
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

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getOtp(
        context: Context,
        phone: String,
        countryCode: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: OtpCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "phone" to phone,
            "phone_country" to countryCode,
            "vc" to NetworkUtils.getVCKey(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "lang" to AppUtil.language,
            "os" to NetworkUtils.getOsVersion(),
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Merge additional fields from host app
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getOTP(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("OTP API failed: ${response.code()}")
                    }
                }

            } catch (e: SocketTimeoutException) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("OTP request timed out")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }
    }



    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getOtpVerify(
        context: Context,
        phone: String,
        pin: String,
        countryCode: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: OtpCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "phone" to phone,
            "phone_country" to countryCode,
            "pin" to pin,
            "vc" to NetworkUtils.getVCKey(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "lang" to AppUtil.language,
            "os" to NetworkUtils.getOsVersion(),
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Merge additional fields from host app
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getOtpVerify(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("OTP API failed: ${response.code()}")
                    }
                }

            } catch (e: SocketTimeoutException) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("OTP request timed out")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }
    }


}

