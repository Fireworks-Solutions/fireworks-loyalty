package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.DirectoryCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.DirectoryCategoryCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.DirectoryDetailCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.DirectoryFloorCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.DirectorySearchCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
//test
object DirectorySdk {


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
    fun getDirectoryDetail(
        context: Context,
        custID: String,
        token: String,
        merchantId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: DirectoryDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fieldMap = mutableMapOf(
            "custid" to custID,
            "mall" to AppUtil.mall.toString(),
            "merchantid" to merchantId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        // Host app can add fields like "language", "version", etc.
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directoryDetailAPI(fieldMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Directory API failed: ${response.code()}")
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
    fun getDirectory(
        context: Context,
        categoryId: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: DirectoryCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fieldMap = mutableMapOf(
            "categoryid" to categoryId,
            "mall" to AppUtil.mall.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        // Add optional params provided by host app
        fieldMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directoryAPI(fieldMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Directory API failed: ${response.code()}")
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
    fun searchDirectory(
        context: Context,
        searchTerm: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: DirectorySearchCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "mall" to AppUtil.mall.toString(),
            "searchterm" to searchTerm,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        // Merge additional fields
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directorySearchAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Directory search failed: ${response.code()}")
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
    fun getDirectoryCategory(
        context: Context,
        token: String,
        merchantId: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: DirectoryCategoryCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "merchantid" to merchantId,
            "mall" to AppUtil.mall.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directoryCategoryAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Directory category failed: ${response.code()}")
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
    fun getDirectoryFloor(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: DirectoryFloorCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "mall" to AppUtil.mall.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directoryFloorAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Directory floor failed: ${response.code()}")
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




}