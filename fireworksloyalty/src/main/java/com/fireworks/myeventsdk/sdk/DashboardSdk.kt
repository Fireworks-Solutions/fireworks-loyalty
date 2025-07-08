package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.DashboardCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.DeviceTokenCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.InAppAlertCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.MultiMallCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.PayablePointsCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.RatePurchaseCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.PrefConstant
import com.incredibleqr.mysogo.util.offline_caching.GetJsonResponse
import com.incredibleqr.mysogo.util.offline_caching.OfflineCacheUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException

object DashboardSdk {

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
    fun ratePurchase(
        context: Context,
        custId: String,
        purchaseId: String,
        rating: String,
        callback: RatePurchaseCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.ratePurchase(
                    custid = appPreference.getString(PrefConstant.CUSTOMER_ID)?:"",
                    purchaseid = purchaseId,
                    rating = rating,
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
                        callback.onFailure("Rating failed with status: ${response.code()}")
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
    fun dashboardAPI(
        context: Context,
        custId: String,
        mercId: String,
        appversion: String,
        pv: String,
        deviceflavour : String,
        callback: DashboardCallback
    ) {
        val cachedDashboard = GetJsonResponse.getDashboardApi()

        if (cachedDashboard != null && !OfflineCacheUtils.getIfCanFetchOnlineData()) {
            AppUtil.dashboardResponse = cachedDashboard
            callback.onSuccess(cachedDashboard)
            return
        }

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")

            AppUtil.dashboardResponse?.let {
                callback.onSuccess(it)
            }
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.dashboardAPI(
                    mall = AppUtil.currentMall.toString(),
                    custId = appPreference.getString(PrefConstant.CUSTOMER_ID)?:"",
                    merchantId = mercId,
                    latitude = AppUtil.latitude,
                    longitude = AppUtil.longitude,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    appVersion = appversion,
                    pv = pv,
                    sv = Constants.svc,
                    deviceflavour = deviceflavour
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        AppUtil.dashboardResponse = response.body()!!
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Dashboard API failed: ${response.code()}")
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
    fun multiMallAPI(
        context: Context,
        callback: MultiMallCallback
    ) {
        val cachedMallList = GetJsonResponse.getMultiMallApi()

        if (cachedMallList != null && !OfflineCacheUtils.getIfCanFetchOnlineData()) {
            callback.onSuccess(cachedMallList)
            return
        }

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getMallList(
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
                        callback.onFailure("Failed with status: ${response.code()}")
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
    fun addDeviceTokenAPI(
        context: Context,
        token: String,
        custId: String,
        callback: DeviceTokenCallback? = null
    ) {
        if (OfflineCacheUtils.isDataAvailable()) return

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback?.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.addDeviceTokenAPI(
                    token = token,
                    custId = appPreference.getString(PrefConstant.CUSTOMER_ID)?:"",
                    deviceId = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
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
                    if (response.isSuccessful) {
                        callback?.onSuccess()
                    } else {
                        callback?.onFailure("Failed with status: ${response.code()}")
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback?.onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }
    }


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun inAppAlertsAPI(
        context: Context,
        customerId: String,
        callback: InAppAlertCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.inAppAlertsAPI(
                    vc = NetworkUtils.getVCKey(),
                    date = NetworkUtils.unixTimeStamp().toString(),
                    custId = appPreference.getString(PrefConstant.CUSTOMER_ID)?:"",
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("API failed with status: ${response.code()}")
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
    fun updatePayableByPoints(
        context: Context,
        customerId: String,
        callback: PayablePointsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.updatePayableByPoints(
                    vc = NetworkUtils.getVCKey(),
                    date = NetworkUtils.unixTimeStamp().toString(),
                    customerID = appPreference.getString(PrefConstant.CUSTOMER_ID)?:"",
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess()
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