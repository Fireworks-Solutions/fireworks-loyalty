package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface
import com.fireworks.myeventsdk.Utils.CommonInterface.AddPointsCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.PasswordUpdateCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.ProfileUpdateCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.SetPasswordCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.Utils.NetworkUtils.deviceId
import com.fireworks.myeventsdk.Utils.PrefConstant
import com.fireworks.myeventsdk.model.Profile.ProfileResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProfileSdk {

    //test
    private lateinit var retrofitService: Service
    private lateinit var appPreference: AppPreference


    fun init(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        ProfileSdk.retrofitService = retrofit.create(Service::class.java)
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun profileAPI(
        context: Context,
        merchantId: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: CommonInterface.ProfileCallback
    ) {
        appPreference = AppPreference.getInstance(context)

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val baseParams = mutableMapOf(
            "custid" to (appPreference.getString(PrefConstant.CUSTOMER_ID) ?: ""),
            "mercid" to merchantId,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc,
            "pvc" to NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
        )

        // Merge any extra fields passed from the host app
        baseParams.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.profileAPI(baseParams)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        val profile = response.body()!!

                        // Optional: cache profile
                        appPreference.putString(PrefConstant.USER_FNAME, profile.profile?.fname)
                        appPreference.putString(PrefConstant.USER_LNAME, profile.profile?.lname)

                        Log.d("ProfileSdk", "Profile API success: $profile")
                        callback.onSuccess(profile)
                    } else {
                        callback.onFailure("Profile API failed with status: ${response.code()}")
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
    fun updateProfileAPI(
        context: Context,
        custId: String,
        merchantId: String,
        title: String,
        fName: String,
        lName: String,
        email: String,
        phone: String,
        countryCode: String,
        dob: String,
        nric: String,
        idType: Int,
        prefName: String,
        gender: String,
        anniv: String,
        nationality: String,
        country: String,
        race: String,
        income: Int,
        interests: String,
        token: String,
        addressone: String,
        addresstwo: String,
        state: String,
        city: String,
        postcode: String,
        preferredMall: Int,
        callback: ProfileUpdateCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val sRace = if (race == "Choose Race") "" else race
        val sTitle = if (title == "Choose Title") "" else title
        val sState = if (state == "Select State") "" else state
        val sGender = when (gender) {
            "Choose Gender" -> ""
            "Male" -> "1"
            "Female" -> "2"
            else -> gender
        }

        val fields = mutableMapOf(
            "custid" to custId,
            "mercid" to merchantId,
            "title" to sTitle,
            "fname" to fName,
            "lname" to lName,
            "email" to email,
            "phone" to phone,
            "phone_country" to countryCode,
            "dob" to dob,
            "nric" to nric,
            "id_type" to idType.toString(),
            "display_name" to prefName,
            "gender" to sGender,
            "love_anniversary" to anniv,
            "nationality" to nationality,
            "country" to country,
            "race" to sRace,
            "householdincome" to income.toString(),
            "selectedinterests" to interests,
            "address1" to addressone,
            "address2" to addresstwo,
            "state" to sState,
            "city" to city,
            "postcode" to postcode,
            "mall" to preferredMall.toString(),
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc,
            "pvc" to NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.updateProfileAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Update failed with status: ${response.code()}")
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
    fun addPoints(
        context: Context,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: AddPointsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Default fields
                val params = mutableMapOf(
                    "reward_type" to "complete_profile",
                    "custid" to appPreference.getString(PrefConstant.CUSTOMER_ID).orEmpty(),
                    "date" to NetworkUtils.unixTimeStamp().toString(),
                    "vc" to NetworkUtils.getVCKey(),
                    "os" to NetworkUtils.getOsVersion(),
                    "phonename" to NetworkUtils.getDeviceName(context),
                    "phonetype" to NetworkUtils.getDeviceLayoutType(context),
                    "sectoken" to token,
                    "svc" to Constants.svc
                )

                // Merge with dynamic fields from host app
                params.putAll(extraParams)

                val response = retrofitService.givePoints(params)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Add points failed with status: ${response.code()}")
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
    fun updatePassword(
        context: Context,
        newPassword: String,
        token: String,
        oldPassword: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: PasswordUpdateCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val custId = appPreference.getString(PrefConstant.CUSTOMER_ID) ?: run {
            callback.onFailure("Customer ID not found")
            return
        }

        val merchantId = appPreference.getString(PrefConstant.MERCHANT_ID) ?: run {
            callback.onFailure("Merchant ID not found")
            return
        }

        val fields = mutableMapOf(
            "custid" to custId,
            "mercid" to merchantId,
            "oldpass" to oldPassword,
            "newpass" to newPassword,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "os" to NetworkUtils.getOsVersion(),
            "phonename" to NetworkUtils.getDeviceName(context),
            "phonetype" to NetworkUtils.getDeviceLayoutType(context),
            "sectoken" to token,
            "lang" to AppUtil.language,
            "deviceid" to AppUtil.getDeviceId(context),
            "devicetype" to NetworkUtils.getDeviceLayoutType(context),
            "svc" to Constants.svc,
            "pvc" to NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
        )

        // Add optional fields from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.changePasswordAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Password update failed: ${response.code()}")
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
    fun setPassword(
        context: Context,
        newPassword: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: SetPasswordCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val custId = appPreference.getString(PrefConstant.CUSTOMER_ID) ?: run {
            callback.onFailure("Customer ID not found")
            return
        }

        val merchantId = appPreference.getString(PrefConstant.MERCHANT_ID) ?: run {
            callback.onFailure("Merchant ID not found")
            return
        }

        val fields = mutableMapOf(
            "custid" to custId,
            "mercid" to merchantId,
            "newpass" to newPassword,
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

        // Merge dynamic fields from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.setPasswordAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Set password failed: ${response.code()}")
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
    fun forgotPassword(
        context: Context,
        type: String,
        email: String,
        phone: String,
        countrycode: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: SetPasswordCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val custId = appPreference.getString(PrefConstant.CUSTOMER_ID) ?: run {
            callback.onFailure("Customer ID not found")
            return
        }

        val merchantId = appPreference.getString(PrefConstant.MERCHANT_ID) ?: run {
            callback.onFailure("Merchant ID not found")
            return
        }

        val fields = mutableMapOf(
            "custid" to custId,
            "mercid" to merchantId,
            "type" to type,
            "email" to email,
            "phone" to phone,
            "phone_country" to countrycode,
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

        // Merge dynamic fields from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.setPasswordAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Set password failed: ${response.code()}")
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
    fun forgot2Password(
        context: Context,
        custId: String,
        merchantId: String,
        type: String,
        email: String,
        phone: String,
        countrycode: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: SetPasswordCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }


        val fields = mutableMapOf(
            "custid" to custId,
            "mercid" to merchantId,
            "type" to type,
            "email" to email,
            "phone" to phone,
            "phone_country" to countrycode,
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

        // Merge dynamic fields from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.fogotPasswordAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Set password failed: ${response.code()}")
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
    fun smsOTpReset(
        context: Context,
        type: String,
        email: String,
        pin : String,
        phone: String,
        countrycode: String,
        token: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: SetPasswordCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val appPreference = AppPreference.getInstance(context)

        val custId = appPreference.getString(PrefConstant.CUSTOMER_ID) ?: run {
            callback.onFailure("Customer ID not found")
            return
        }

        val merchantId = appPreference.getString(PrefConstant.MERCHANT_ID) ?: run {
            callback.onFailure("Merchant ID not found")
            return
        }

        val fields = mutableMapOf(
            "custid" to custId,
            "mercid" to merchantId,
            "type" to type,
            "email" to email,
            "phone" to phone,
            "otp_pin" to pin,
            "phone_country" to countrycode,
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

        // Merge dynamic fields from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.resetOtp(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Set password failed: ${response.code()}")
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
//
//}