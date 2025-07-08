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
        callback: LoginCallback
    ) {
        appPreference = AppPreference.getInstance(context)

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.loginAPI(
                    email = email,
                    phone = "",
                    phoneCountry = "",
                    nric = "",
                    password = password,
                    socialType = "",
                    socialToken = "",
                    merchantId = "44",
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    lang = "en",
                    deviceId = deviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    svc = Constants.svc,
                    pvc = NetworkUtils.getHello(email)
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        val loginResponse = response.body()!!

                        appPreference.putBoolean(PrefConstant.CHANGE_PW, loginResponse.changePassword!!.toBoolean())
                        appPreference.putBoolean(PrefConstant.REGISTER, loginResponse.register!!.toBoolean())
                        appPreference.putString(PrefConstant.URL, loginResponse.url!!)

                        if (loginResponse.sid != null){
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
        callback: OtpCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getOTP(
                    phone = phone,
                    phoneCountry = countryCode,
                    vc = NetworkUtils.getVCKey(),
                    date = NetworkUtils.unixTimeStamp().toString(),
                    lang = AppUtil.language,
                    os = NetworkUtils.getOsVersion(),
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

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

