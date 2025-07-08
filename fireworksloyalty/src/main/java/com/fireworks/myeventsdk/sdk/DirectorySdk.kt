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
        merchantId: String,
        callback: DirectoryDetailCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directoryDetailAPI(
                    custid = custID,
                    mall = AppUtil.mall,
                    merchantId = merchantId,
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
        callback: DirectoryCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directoryAPI(
                    categoryId = categoryId,
                    mall = AppUtil.mall,
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
        callback: DirectorySearchCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directorySearchAPI(
                    mall = AppUtil.mall,
                    searchTerm = searchTerm,
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
        merchantId: String,
        callback: DirectoryCategoryCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directoryCategoryAPI(
                    merchantId = merchantId,
                    mall = AppUtil.mall,
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
        callback: DirectoryFloorCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.directoryFloorAPI(
                    mall = AppUtil.mall,
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