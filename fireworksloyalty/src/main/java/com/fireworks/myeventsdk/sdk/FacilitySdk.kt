package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.FacilityCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.FacilityCategoryCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.FacilityFloorCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.FacilitySearchCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

object FacilitySdk {

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
    fun getFacilities(
        context: Context,
        categoryId: String,
        extraParams: Map<String, String> = emptyMap(),  // allows new optional fields
        callback: FacilityCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val params = mutableMapOf(
            "categoryid" to categoryId,
            "mall" to AppUtil.mall.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "svc" to Constants.svc
        )

        params.putAll(extraParams) // merge host-supplied dynamic parameters

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.facilityAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Facility API failed: ${response.code()}")
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
    fun searchFacilities(
        context: Context,
        searchTerm: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: FacilitySearchCallback
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
            "sectoken" to AppUtil.applicationToken,
            "svc" to Constants.svc
        )

        // Add any additional dynamic parameters
        params.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.facilitySearchAPI(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Facility search failed: ${response.code()}")
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
    fun getFacilityCategories(
        context: Context,
        merchantId: String,
        extraParams: Map<String, String> = emptyMap(),  // allow host to add custom params
        callback: FacilityCategoryCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "merchantid" to merchantId,
            "mall" to AppUtil.mall.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "svc" to Constants.svc
        )

        // Host can override or extend via extraParams
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.facilityCategoryAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Facility category API failed: ${response.code()}")
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
    fun getFacilityFloors(
        context: Context,
        extraParams: Map<String, String> = emptyMap(), // <-- dynamic params
        callback: FacilityFloorCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "mall" to AppUtil.mall.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to AppUtil.applicationToken,
            "svc" to Constants.svc
        )

        // Allow host app to override or add more fields
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.facilityFloorAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Facility floor API failed: ${response.code()}")
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