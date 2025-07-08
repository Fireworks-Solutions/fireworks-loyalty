package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface
import com.fireworks.myeventsdk.Utils.CommonInterface.ReferralDataCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.SettingsCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.SettingsUpdateCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.StatesCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.SupportCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.UsefulLinkCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.PrefConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

object SettingsSdk {


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
    fun getStates(
        context: Context,
        callback: StatesCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getStates(
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
                        callback.onFailure("States API failed: ${response.code()}")
                    }
                }

            } catch (e: SocketTimeoutException) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("Request timed out")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getReferralData(
        context: Context,
        custId: String,
        callback: ReferralDataCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getReferData(
                    vc = NetworkUtils.getVCKey(),
                    date = NetworkUtils.unixTimeStamp().toString(),
                    custId = custId,
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Referral API failed: ${response.code()}")
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
    fun getMemberCenterList(
        context: Context,
        callback: UsefulLinkCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getUsefulLinks(
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Useful Links API failed: ${response.code()}")
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
    fun getSettings(context: Context, callback: SettingsCallback) {
        val appPreference = AppPreference.getInstance(context)
        val custId = appPreference.getString(PrefConstant.CUSTOMER_ID) ?: ""

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getSettingsAPI(
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    custId = custId,
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    os = NetworkUtils.getOsVersion(),
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceName(context),
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed with code ${response.code()}")
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
    fun updateSettings(
        context: Context,
        setting: String,
        notifications: Boolean,
        nearby: Boolean,
        generali: Boolean,
        callback: SettingsUpdateCallback
    ) {
        val appPreference = AppPreference.getInstance(context)
        val custId = appPreference.getString(PrefConstant.CUSTOMER_ID) ?: ""

        val general = if (generali) "1" else "0"
        val news = if (notifications) "1" else "0"
        val righthere = if (nearby) "1" else "0"

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.updateSettingsAPI(
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    custId = custId,
                    setting = setting,
                    general = general,
                    news = news,
                    righthere = righthere,
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    os = NetworkUtils.getOsVersion(),
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceName(context),
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Update failed: ${response.code()}")
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
    fun supportAPI(
        context: Context,
        name: String,
        phone: String,
        email: String,
        subject: String,
        description: String,
        image: String,
        callback: SupportCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No internet connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.supportAPI(
                    name = name,
                    phone = phone,
                    email = email,
                    subject = subject,
                    issueType = "General Query",
                    description = description,
                    image = image,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceName(context),
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Support API failed with status: ${response.code()}")
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