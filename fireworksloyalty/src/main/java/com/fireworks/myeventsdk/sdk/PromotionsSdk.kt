package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.NewsDetailCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.NewsListCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PromotionsSdk {

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
    fun getNewsDetail(
        context: Context,
        newsId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: NewsDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "mall" to AppUtil.currentMall.toString(),
            "newsid" to newsId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Allow overriding or adding new fields
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.newsDetailAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("News detail fetch failed: ${response.code()}")
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
    fun getNews(
        context: Context,
        start: Int,
        category: String,
        latest: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: NewsListCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "mall" to AppUtil.currentMall.toString(),
            "latest" to latest,
            "start" to start.toString(),
            "category" to category,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc
        )

        // Merge any additional parameters from the host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.newsAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("News API failed: ${response.code()}")
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