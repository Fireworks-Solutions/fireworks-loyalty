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
        extraParams: Map<String, String> = emptyMap(), // For host flexibility
        callback: TransactionCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No internet connection")
            return
        }

        val sArchive = if (archive) "1" else "0"

        val fields = mutableMapOf(
            "custid" to custId,
            "archive" to sArchive,
            "merchantid" to merchantId,
            "month" to month,
            "year" to year,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceName(context),
            "svc" to Constants.svc
        )

        // Merge optional fields from host
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.transactionAPI(fields)

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
        extraParams: Map<String, String> = emptyMap(),
        callback: TransactionDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No internet connection")
            return
        }

        val fields = mutableMapOf(
            "type" to type,
            "transid" to transId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceName(context),
            "svc" to Constants.svc
        )

        // Merge dynamic/optional params from host
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.transactionDetailAPI(fields)

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
        extraParams: Map<String, String> = emptyMap(),
        callback: ReleasePointsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "receipt_no" to receiptNo,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        // Merge optional dynamic fields from host
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.releasePoints(fields)

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
    fun updateTransactionArchiveStatus(
        context: Context,
        transId: String,
        archive: String = "1", // "1" for archive, "0" for unarchive
        extraParams: Map<String, String> = emptyMap(),
        callback: TransactionArchiveCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "transid" to transId,
            "archive" to archive,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "svc" to Constants.svc
        )
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.archiveTransaction(fields)

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
    fun updateTransactionUnArchiveStatus(
        context: Context,
        transId: String,
        archive: String = "0",
        extraParams: Map<String, String> = emptyMap(),
        callback: TransactionArchiveCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "transid" to transId,
            "archive" to archive,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "svc" to Constants.svc
        )
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.archiveTransaction(fields)

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