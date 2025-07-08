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
import com.fireworks.myeventsdk.Utils.CommonInterface.RewardCheckoutCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.RewardDetailCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.RewardListCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.ShippingPointCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.VerifyPasswordCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.WalletCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.WalletDetailCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.PrefConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

object RewardsSdk {

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
    fun getDailyRewards(
        context: Context,
        custId: String,
        callback: DailyRewardsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getDailyRewards(
                    custId = custId,
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
        callback: CheckInCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.checkInReward(
                    custId = custId,
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
        id: String,
        type: String,
        callback: WalletDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.walletDetailAPI(
                    custId = custId,
                    merchantId = merchantId,
                    walletType = type,
                    id = id,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

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
        merchantId: String,
        walletType: String,
        condition: String,
        start: Int,
        callback: WalletCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.walletAPI(
                    mall = AppUtil.currentMall.toString(),
                    custId = custId,
                    merchantId = merchantId,
                    walletType = walletType,
                    codition = condition,
                    start = start,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

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
        password: String,
        callback: VerifyPasswordCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.verifyPasswordAPI(
                    custId = custId,
                    password = password,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Password verification failed: ${response.code()}")
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
    fun pickupCheckout(
        context: Context,
        itemId: String,
        custId: String,
        quantity: String,
        method: String,
        callback: PickupCheckoutCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.pickupCheckoutAPI(
                    itemId = itemId,
                    custId = custId,
                    quantity = quantity,
                    method = method,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

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
        custId: String,
        quantity: String,
        method: String,
        callback: RewardCheckoutCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardCheckoutAPI(
                    itemId = itemId,
                    custId = custId,
                    quantity = quantity,
                    method = method,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc,
                    pv = NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
                )

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
        rewardId: String,
        stateName: String,
        callback: ShippingPointCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getShippingPointsByName(
                    rewardId = rewardId,
                    stateName = stateName,
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
    fun getRewardDetail(
        context: Context,
        custId: String,
        merchantId: String,
        couponId: String,
        callback: RewardDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardDetailAPI(
                    custId = custId,
                    merchantId = merchantId,
                    couponId = couponId,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc,
                    pv = NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
                )

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
    fun getReward(
        context: Context,
        custId: String,
        merchantId: String,
        offset: String,
        location: String,
        lat: String,
        long: String,
        lang: String,
        categoryId: String,
        start: Int,
        callback: RewardListCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardAPI(
                    mall = AppUtil.currentMall.toString(),
                    custId = custId,
                    merchantId = merchantId,
                    offset = offset,
                    location = location,
                    latitude = lat,
                    longitude = long,
                    lang = lang,
                    categoryId = categoryId,
                    start = start,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

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
        merchantId: String,
        callback: RewardCategoryCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.rewardCategoryAPI(
                    merchantId = merchantId,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

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
        merchantId: String,
        type: String,
        id: String,
        condition: String,
        callback: MultiWalletCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.walletMultipleAPI(
                    mall = AppUtil.currentMall.toString(),
                    custId = custId,
                    merchantId = merchantId,
                    walletType = type,
                    id = id,
                    condition = condition,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

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