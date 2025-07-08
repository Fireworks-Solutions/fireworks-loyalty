package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.AppUtil.Companion.applicationToken
import com.fireworks.myeventsdk.Utils.CommonInterface.ArchiveNotificationCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.NotificationCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NotificationSdk {

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
        read: String,
        archive: String,
        callback: NotificationCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getInbox(
                    custid = custId,
                    read = read,
                    archive = archive,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = applicationToken,
                    lang = AppUtil.language,
                    sv = Constants.svc
                )

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
        archive: String,
        read: String,
        type: String,
        custId: String,
        inboxId: String,
        callback: ArchiveNotificationCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.inboxAction(
                    custid = custId,
                    read = read,
                    archive = archive,
                    all = "",
                    type = type,
                    inboxid = inboxId,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = applicationToken,
                    lang = AppUtil.language,
                    sv = Constants.svc
                )

                withContext(Dispatchers.Main) {
                    if (response.body()?.status?.contains("get new token", ignoreCase = true) == true) {
                        callback.onTokenExpired {
                            archiveNotification(context, custId, inboxId, archive,read,type, callback)
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
                        // Retry on network/token error
                        archiveNotification(context, custId, inboxId, archive,read,type, callback)
                    }
                }
            }
        }
    }



}