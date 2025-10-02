package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.CheckInCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.DailyRewardsCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.MultiWalletCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.PickupCheckoutCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.RewardCategoryCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.RewardCategoryNewCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.RewardCheckoutCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.RewardDetailCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.RewardListCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.ShippingPointCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.TimerRewardCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.VerifyPasswordCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.WalletCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.WalletDetailCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.getDirectoryListInRewardCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.getSaluationCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.getSupportTypeCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.PrefConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

object RewardsSdk {
    //test
    private lateinit var retrofitService: Service

    private lateinit var appPreference: AppPreference
    val okHttp = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .callTimeout(60, TimeUnit.SECONDS)         // whole-call budget
        .retryOnConnectionFailure(true)
        .pingInterval(15, TimeUnit.SECONDS)        // keep-alive over HTTP/2
        .build()

    fun init(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(Service::class.java)
    }


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getDailyRewards(
        context: Context,
        token: String,
        custId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: DailyRewardsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "custid" to custId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        // Merge dynamic fields from host app
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getDailyRewards(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("API failed with code: ${response.code()}")
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
    fun checkIn(
        context: Context,
        custId: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(), // <- Optional additional fields
        callback: CheckInCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "custid" to custId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        params.putAll(extraParams) // <- Merge any custom fields

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.checkInReward(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Check-in failed: ${response.code()}")
                    }
                }

            } catch (e: SocketTimeoutException) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("Check-in timed out")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }
    }


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getStampRewards(
        context: Context,
        token: String,
        custId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: DailyRewardsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "custid" to custId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        // Merge dynamic fields from host app
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getStampRewards(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("API failed with code: ${response.code()}")
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
    fun StampcheckIn(
        context: Context,
        custId: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(), // <- Optional additional fields
        callback: CheckInCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "custid" to custId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        params.putAll(extraParams) // <- Merge any custom fields

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.checkInStampReward(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Check-in failed: ${response.code()}")
                    }
                }

            } catch (e: SocketTimeoutException) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("Check-in timed out")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }
    }





    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun walletDetailAPI(
        context: Context,
        custId: String,
        merchantId: String,
        token: String,
        id: String,
        type: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: WalletDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "custid" to custId,
            "mercid" to merchantId,
            "type" to type,
            "id" to id,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        params.putAll(extraParams) // Allow host app to send additional key-value pairs

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.walletDetailAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Wallet detail failed with code: ${response.code()}")
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
    fun getWallet(
        context: Context,
        custId: String,
        mall: String,
        merchantId: String,
        token: String,
        walletType: String,
        condition: String,
        start: Int,
        extraParams: Map<String, String> = emptyMap(), // <-- Allow extra dynamic fields
        callback: WalletCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "mall" to mall,
            "custid" to custId,
            "mercid" to merchantId,
            "type" to walletType,
            "condition" to condition,
            "start" to start.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token ,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Append dynamic fields (e.g., searchTerm)
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.walletAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Wallet fetch failed with status: ${response.code()}")
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
    fun verifyPassword(
        context: Context,
        custId: String,
        token: String,
        password: String,
        extraParams: Map<String, String> = emptyMap(), // Host app can add more fields
        callback: VerifyPasswordCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val baseParams = mutableMapOf(
            "custid" to custId,
            "pass" to password,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Host can override or add extra fields
        baseParams.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.verifyPasswordAPI(baseParams)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed: ${response.code()}")
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



//    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
//    fun verifyPassword(
//        context: Context,
//        custId: String,
//        password: String,
//        callback: VerifyPasswordCallback
//    ) {
//        if (!NetworkUtils.isInternetAvailable(context)) {
//            callback.onFailure("No Internet Connection")
//            return
//        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = retrofitService.verifyPasswordAPI(
//                    custId = custId,
//                    password = password,
//                    date = NetworkUtils.unixTimeStamp().toString(),
//                    vc = NetworkUtils.getVCKey(),
//                    os = NetworkUtils.getOsVersion(),
//                    phoneName = NetworkUtils.getDeviceName(context),
//                    phoneType = NetworkUtils.getDeviceLayoutType(context),
//                    sectoken = AppUtil.applicationToken,
//                    lang = AppUtil.language,
//                    deviceid = AppUtil.getDeviceId(context),
//                    deviceType = NetworkUtils.getDeviceLayoutType(context),
//                    sv = Constants.svc
//                )
//
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful && response.body() != null) {
//                        callback.onSuccess(response.body()!!)
//                    } else {
//                        callback.onFailure("Password verification failed: ${response.code()}")
//                    }
//                }
//
//            } catch (e: SocketTimeoutException) {
//                withContext(Dispatchers.Main) {
//                    callback.onFailure("Request timed out")
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    callback.onFailure(e.localizedMessage ?: "Unexpected error")
//                }
//            }
//        }
//    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun pickupCheckout(
        context: Context,
        itemId: String,
        custId: String,
        quantity: String,
        token: String,
        method: String,
        extraParams: Map<String, String> = emptyMap(), // Optional
        callback: PickupCheckoutCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fieldMap = mutableMapOf(
            "itemid" to itemId,
            "custid" to custId,
            "qty" to quantity,
            "collection_method" to method,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Merge any dynamic fields from host
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.pickupCheckoutAPI(fieldMap)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Checkout failed: ${response.code()}")
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
    fun rewardCheckout(
        context: Context,
        itemId: String,
        token: String,
        custId: String,
        quantity: String,
        method: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: RewardCheckoutCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        appPreference = AppPreference.getInstance(context)

        val fieldMap = mutableMapOf(
            "itemid" to itemId,
            "custid" to custId,
            "qty" to quantity,
            "collection_method" to method,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc,
            "pvc" to NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
        )

        // Allow host app to pass any additional dynamic fields
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardCheckoutAPI(fieldMap)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Checkout failed: ${response.code()}")
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
    fun getTimerReward(
        context: Context,
        itemId: String,
        token: String,
        custId: String,
        quantity: String,
        method: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: TimerRewardCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        appPreference = AppPreference.getInstance(context)

        val fieldMap = mutableMapOf(
            "id" to itemId,
            "custid" to custId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc,
            "pvc" to NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
        )

        // Allow host app to pass any additional dynamic fields
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.timerRewardAPI(fieldMap)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Checkout failed: ${response.code()}")
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
    fun getShippingPointByName(
        context: Context,
        token: String,
        rewardId: String,
        stateId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: ShippingPointCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val requestMap = mutableMapOf(
            "reward_id" to rewardId,
            "stateid" to stateId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        requestMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getShippingPointsByName(requestMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Shipping point fetch failed: ${response.code()}")
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
    fun getDirectoryListInReward(
        context: Context,
        token: String,
        custId: String,
        lat: String,
        long: String,
        primaryfilter: String,
        rewardId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: getDirectoryListInRewardCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val requestMap = mutableMapOf(
            "reward_id" to rewardId,
            "custid" to custId,
            "primary_filter" to primaryfilter,
            "lon" to long,
            "lat" to lat,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        requestMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getDirectoryListInReward(requestMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Shipping point fetch failed: ${response.code()}")
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
    fun getSalutation(
        context: Context,
        token: String,

        extraParams: Map<String, String> = emptyMap(),
        callback: getSaluationCallback
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
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        requestMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getSalutation(requestMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Salutation failed: ${response.code()}")
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
    fun getSupportType(
        context: Context,
        token: String,

        extraParams: Map<String, String> = emptyMap(),
        callback: getSupportTypeCallback
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
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        requestMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getSupportType(requestMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("SupporT failed: ${response.code()}")
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
    fun getRewardDetail(
        context: Context,
        custId: String,
        token: String,
        merchantId: String,
        couponId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: RewardDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val requestMap = mutableMapOf(
            "custid" to custId,
            "mercid" to merchantId,
            "id" to couponId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc,
            "pvc" to NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
        )

        requestMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardDetailAPI(requestMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Reward detail failed: ${response.code()}")
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
    fun getNearByReward(
        context: Context,
        custId: String,
        mall: String,
        merchantId: String,
        offset: String,
        token: String,
        location: String,
        lat: String,
        long: String,
        lang: String,
        categoryId: String,
        start: Int,
        extraParams: Map<String, String> = emptyMap(),
        callback: RewardListCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val fieldMap = mutableMapOf(
            "mall" to mall,
            "custid" to custId,
            "mercid" to merchantId,
            "offset" to offset,
            "location" to location,
            "latitude" to lat,
            "longitude" to long,
            "category" to categoryId,
            "start" to start.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to lang,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Add any dynamic fields from the host app
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.nearByrewardAPI(fieldMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed to fetch rewards: ${response.code()}")
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
    fun getReward(
        context: Context,
        custId: String,
        mall: String,
        merchantId: String,
        offset: String,
        token: String,
        location: String,
        lat: String,
        long: String,
        lang: String,
        categoryId: String,
        start: Int,
        extraParams: Map<String, String> = emptyMap(),
        callback: RewardListCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val fieldMap = mutableMapOf(
            "mall" to mall,
            "custid" to custId,
            "mercid" to merchantId,
            "offset" to offset,
            "location" to location,
            "latitude" to lat,
            "longitude" to long,
            "category" to categoryId,
            "start" to start.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to lang,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Add any dynamic fields from the host app
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardAPI(fieldMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed to fetch rewards: ${response.code()}")
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
    fun getNewReward(
        context: Context,
        custId: String,
        mall: String,
        merchantId: String,
        offset: String,
        token: String,
        location: String,
        lat: String,
        long: String,
        lang: String,
        categoryId: String,
        start: Int,
        extraParams: Map<String, String> = emptyMap(),
        callback: RewardListCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val fieldMap = mutableMapOf(
            "mall" to mall,
            "custid" to custId,
            "mercid" to merchantId,
            "offset" to offset,
            "location" to location,
            "latitude" to lat,
            "longitude" to long,
            "category" to categoryId,
            "start" to start.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to lang,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Add any dynamic fields from the host app
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardnNewAPI(fieldMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed to fetch rewards: ${response.code()}")
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
    fun getRewardCategory(
        context: Context,
        token: String,
        merchantId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: RewardCategoryCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fieldMap = mutableMapOf(
            "mercid" to merchantId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Add optional fields from host app
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardCategoryAPI(fieldMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Reward category failed: ${response.code()}")
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
    fun getNewRewardCategory(
        context: Context,
        token: String,
        merchantId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: RewardCategoryNewCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fieldMap = mutableMapOf(
            "mercid" to merchantId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Add optional fields from host app
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardCategoriesAPI(fieldMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Reward category failed: ${response.code()}")
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
    fun getMultiWallet(
        context: Context,
        custId: String,
        mall: String,
        token: String,
        merchantId: String,
        type: String,
        id: String,
        condition: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: MultiWalletCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "mall" to mall,
            "custid" to custId,
            "mercid" to merchantId,
            "type" to type,
            "id" to id,
            "condition" to condition,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Inject dynamic fields from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.walletMultipleAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("API failed with status: ${response.code()}")
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