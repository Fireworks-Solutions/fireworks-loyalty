package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.ReleasePointsCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.TransactionArchiveCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.TransactionCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.TransactionDetailCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TransactionSdk {

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
    fun getTransaction(
        context: Context,
        custId: String,
        merchantId: String,
        month: String,
        year: String,
        archive: Boolean,
        callback: TransactionCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No internet connection")
            return
        }

        val sArchive = if (archive) "1" else "0"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.transactionAPI(
                    custId = custId,
                    archive = sArchive,
                    merchantId = merchantId,
                    month = month,
                    year = year,
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
                        callback.onFailure("Transaction API failed: ${response.code()}")
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
    fun getTransactionDetail(
        context: Context,
        type: String,
        transId: String,
        callback: TransactionDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No internet connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService
                    .transactionDetailAPI(
                        type = type,
                        transactionId = transId,
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
                        callback.onFailure("Failed with status: ${response.code()}")
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
    fun releasePoints(
        context: Context,
        receiptNo: String,
        callback: ReleasePointsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.releasePoints(
                    reciptNo = receiptNo,
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
                        callback.onFailure("API failed with status: ${response.code()}")
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
    fun archiveTransaction(
        context: Context,
        transId: String,
        callback: TransactionArchiveCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.archiveTransaction(
                    transId = transId,
                    archive = "1",
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

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun unarchiveTransaction(
        context: Context,
        transId: String,
        archive : String,
        callback: TransactionArchiveCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.archiveTransaction(
                    transId = transId,
                    archive = archive,
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