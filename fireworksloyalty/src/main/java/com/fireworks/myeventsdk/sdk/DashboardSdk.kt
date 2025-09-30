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
import com.fireworks.myeventsdk.Utils.CommonInterface.PrivCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.PrivRankCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.PurchaseCountCallBack
import com.fireworks.myeventsdk.Utils.CommonInterface.RatePurchaseCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.StatesCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.PrefConstant
import com.fireworks.myeventsdk.model.offline_caching.GetJsonResponse
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
//test
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
        purchaseId: String,
        rating: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: RatePurchaseCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "custid" to (appPreference.getString(PrefConstant.CUSTOMER_ID) ?: ""),
            "purchaseid" to purchaseId,
            "rating" to rating,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        // Host can override or append extra values
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.ratePurchase(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                        Log.d("ratePurchase", "successful: $response")

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
    fun getPurchseCount(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: PurchaseCountCallBack
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val requestMap = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        requestMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.purchaseCountApi(requestMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Purchsee API failed: ${response.code()}")
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
    fun dashboardAPI(
        context: Context,
        mall: String,
        custId: String,
        mercId: String,
        token: String,
        appVersion: String,
        pv: String,
        deviceFlavour: String,
        extraParams: Map<String, String> = emptyMap(),
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
            AppUtil.dashboardResponse?.let { callback.onSuccess(it) }
            return
        }

        val params = mutableMapOf(
            "mall" to mall,
            "custid" to custId,
            "mercid" to mercId,
            "lat" to AppUtil.latitude.toString(),
            "lng" to AppUtil.longitude.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "appversion" to appVersion,
            "deviceflavour" to deviceFlavour,
            "svc" to Constants.svc,
            "pvc" to pv
        )

        // Allow host app to add or override any param
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.dashboardAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.d("Dashboard", "successful: $response")

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
    fun privdashboardAPI(
        context: Context,
        mall: String,
        custId: String,
        mercId: String,
        token: String,
        appVersion: String,
        pv: String,
        deviceFlavour: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: PrivCallback
    ) {

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "mall" to mall,
            "custid" to custId,
            "mercid" to mercId,
            "lat" to AppUtil.latitude.toString(),
            "lng" to AppUtil.longitude.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "appversion" to appVersion,
            "deviceflavour" to deviceFlavour,
            "svc" to Constants.svc,
            "pvc" to pv
        )

        // Allow host app to add or override any param
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.privDashAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.d("Dashboard", "successful: $response")

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
    fun privRankAPI(
        context: Context,
        mall: String,
        custId: String,
        mercId: String,
        token: String,
        appVersion: String,
        pv: String,
        deviceFlavour: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: PrivRankCallback
    ) {

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "mall" to mall,
            "custid" to custId,
            "mercid" to mercId,
            "lat" to AppUtil.latitude.toString(),
            "lng" to AppUtil.longitude.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "appversion" to appVersion,
            "deviceflavour" to deviceFlavour,
            "svc" to Constants.svc,
            "pvc" to pv
        )

        // Allow host app to add or override any param
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.privRankAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.d("Dashboard", "successful: $response")

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
        token: String,
        extraParams: Map<String, String> = emptyMap(),
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

        val params = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        params.putAll(extraParams) // Host can override or add extra fields

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getMallList(params)

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
        extraParams: Map<String, String> = emptyMap(),
        callback: DeviceTokenCallback? = null
    ) {
        if (OfflineCacheUtils.isDataAvailable()) return

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback?.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "token" to token,
            "custid" to custId,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        // Inject extra host-defined fields
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.addDeviceTokenAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        callback?.onSuccess()
                        Log.d("devicetoken", "successful: $response")

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
        extraParams: Map<String, String> = emptyMap(),
        callback: InAppAlertCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "vc" to NetworkUtils.getVCKey(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "custid" to customerId,
            "svc" to Constants.svc
        )

        // Append any extra dynamic fields passed from the host app
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.inAppAlertsAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                        Log.d("alerts", "successful: $response")

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
        extraParams: Map<String, String> = emptyMap(),
        callback: PayablePointsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "vc" to NetworkUtils.getVCKey(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "custid" to customerId,
            "svc" to Constants.svc
        )

        // Merge host-app-provided extra params
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.updatePayableByPoints(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess()
                        Log.d("points", "successful: $response")

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