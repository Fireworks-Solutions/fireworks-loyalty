package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.ArchiveNotificationCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.ClearNotCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.NotificationCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.ReleasePointsCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NotificationSdk {
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
    fun getNotifications(
        context: Context,
        custId: String,
        token: String,
        read: String,
        archive: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: NotificationCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "custid" to custId,
            "read" to read,
            "archive" to archive,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        // Merge extra parameters from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getInbox(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Inbox API failed: ${response.code()}")
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
    fun archiveNotification(
        context: Context,
        custId: String,
        inboxId: String,
        archive: String,
        read: String,
        token: String,
        type: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: ArchiveNotificationCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "custid" to custId,
            "read" to read,
            "archive" to archive,
            "all" to "",
            "type" to type,
            "inbox_id" to inboxId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        // Add extra dynamic params if needed
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.inboxAction(fields)

                withContext(Dispatchers.Main) {
                    if (response.body()?.status?.contains("get new token", ignoreCase = true) == true) {
                        callback.onTokenExpired {
                            archiveNotification(context, custId, inboxId, archive, read, type, token,extraParams, callback)
                        }
                    } else if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Archive failed: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onTokenExpired {
                        archiveNotification(context, custId, inboxId, archive, read, type, token,extraParams, callback)
                    }
                }
            }
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun clearNotification(
        context: Context,
        custId: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: ClearNotCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
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

        // Merge optional dynamic fields from host
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.clearNotification(fields)

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


}