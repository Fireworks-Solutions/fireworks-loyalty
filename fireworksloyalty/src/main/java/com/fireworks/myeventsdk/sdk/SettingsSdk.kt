package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface
import com.fireworks.myeventsdk.Utils.CommonInterface.CountryCodesCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.CountryListCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.GetArticleDetailsCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.GetGlobalSearchCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.ReferralDataCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.SettingsCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.SettingsUpdateCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.StatesCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.SupportCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.UsefulLinkCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.PrefConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

object SettingsSdk {
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
    fun getStates(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: StatesCallback
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
            "svc" to Constants.svc
        )

        requestMap.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getStates(requestMap)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("States API failed: ${response.code()}")
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
    fun getGlobalSearch(
        context: Context,
        id : String,
        searchTerm: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: GetGlobalSearchCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "custid" to id,
            "searchterm" to searchTerm,
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

        // Merge any additional parameters from the host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.searchFilter(fields)

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


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getCountryList(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: CountryListCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        fields.putAll(extraParams) // Add dynamic parameters from host app

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getCountryList(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Country list failed: ${response.code()}")
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
    fun getCountryCodes(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: CountryCodesCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "svc" to Constants.svc
        )

        fields.putAll(extraParams) // Add dynamic parameters from host app

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getCountryCodes()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Country list failed: ${response.code()}")
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
    fun getReferralData(
        context: Context,
        custId: String,
        extraParams: Map<String, String> = emptyMap(),  // Optional dynamic parameters
        callback: ReferralDataCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "vc" to NetworkUtils.getVCKey(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "custid" to custId,
            "svc" to Constants.svc
        )

        // Add or override with any extra params from host
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getReferData(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Referral API failed: ${response.code()}")
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
    fun getMemberCenterList(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: UsefulLinkCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "svc" to Constants.svc
        )

        // Add or override with dynamic fields
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getUsefulLinks(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Useful Links API failed: ${response.code()}")
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
    fun getSettings(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: SettingsCallback
    ) {
        val appPreference = AppPreference.getInstance(context)
        val custId = appPreference.getString(PrefConstant.CUSTOMER_ID) ?: ""

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "custid" to custId,
            "sectoken" to token,
            "lang" to AppUtil.language,
            "os" to NetworkUtils.getOsVersion(),
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceName(context),
            "svc" to Constants.svc
        )

        // Allow host to add/override fields dynamically
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getSettingsAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed with code ${response.code()}")
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
    fun updateSettings(
        context: Context,
        setting: String,
        token: String,
        notifications: Boolean,
        nearby: Boolean,
        generali: Boolean,
        extraParams: Map<String, String> = emptyMap(),
        callback: SettingsUpdateCallback
    ) {
        val appPreference = AppPreference.getInstance(context)
        val custId = appPreference.getString(PrefConstant.CUSTOMER_ID) ?: ""

        val general = if (generali) "1" else "0"
        val news = if (notifications) "1" else "0"
        val righthere = if (nearby) "1" else "0"

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "custid" to custId,
            "setting" to setting,
            "general" to general,
            "news" to news,
            "righthere" to righthere,
            "sectoken" to token,
            "lang" to AppUtil.language,
            "os" to NetworkUtils.getOsVersion(),
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceName(context),
            "svc" to Constants.svc
        )

        // Merge in any extra host app parameters
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.updateSettingsAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Update failed: ${response.code()}")
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
    fun supportAPI(
        context: Context,
        name: String,
        phone: String,
        token: String,
        email: String,
        subject: String,
        description: String,
        image: String,
        issueType: String = "General Query",
        extraParams: Map<String, String> = emptyMap(),
        callback: SupportCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No internet connection")
            return
        }

        val fields = mutableMapOf(
            "name" to name,
            "phone" to phone,
            "email" to email,
            "subject" to subject,
            "IssueType" to issueType,
            "description" to description,
            "image" to image,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceName(context),
            "svc" to Constants.svc
        )

        // Merge in optional fields from the host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.supportAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Support API failed with status: ${response.code()}")
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