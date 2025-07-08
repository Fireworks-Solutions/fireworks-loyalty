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
        custId: String,
        merchantId: String,
        callback: CommonInterface.ProfileCallback
    ) {
        appPreference = AppPreference.getInstance(context)

        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.profileAPI(
                    custId = appPreference.getString(PrefConstant.CUSTOMER_ID)?:"",
                    merchantId = merchantId,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc,
                    sectoken = AppUtil.applicationToken,
                    pv = NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        val profile = response.body()!!

                        // Optional: Store profile data if needed
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


        val sRace = if (race == "Choose Race") "" else race
        val sTitle = if (title == "Choose Title") "" else title
        val sState = if (state == "Select State") "" else state
        val sGender = when (gender) {
            "Choose Gender" -> ""
            "Male" -> "1"
            "Female" -> "2"
            else -> gender
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.updateProfileAPI(
                    custId = appPreference.getString(PrefConstant.CUSTOMER_ID)?:"",
                    merchantId = merchantId,
                    title = sTitle,
                    firstName = fName,
                    lastName = lName,
                    email = email,
                    phone = phone,
                    countryCode = countryCode,
                    dob = dob,
                    nric = nric,
                    idType = idType,
                    prefName = prefName,
                    gender = sGender,
                    anniv = anniv,
                    nationality = nationality,
                    country = country,
                    race = sRace,
                    income = income,
                    interests = interests,
                    addressone = addressone,
                    addresstwo = addresstwo,
                    state = sState,
                    city = city,
                    postcode = postcode,
                    preferredMall = preferredMall,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc,
                    pv = NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
                )

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
        custId: String,
        callback: AddPointsCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.givePoints(
                    rewardType = "complete_profile",
                    custId = appPreference.getString(PrefConstant.CUSTOMER_ID)?:"",
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    sv = Constants.svc,
                )

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
        oldPassword: String,
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

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.changePasswordAPI(
                    custId = custId,
                    merchantId = merchantId,
                    oldPassword = oldPassword,
                    newPassword = newPassword,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc,
                    pv = NetworkUtils.getHello(appPreference.getString(PrefConstant.USER_EMAIL) ?: "")
                )

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

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.setPasswordAPI(
                    custId = custId,
                    merchantId = merchantId,
                    newPassword = newPassword,
                    date = NetworkUtils.unixTimeStamp().toString(),
                    vc = NetworkUtils.getVCKey(),
                    os = NetworkUtils.getOsVersion(),
                    phoneName = NetworkUtils.getDeviceName(context),
                    phoneType = NetworkUtils.getDeviceLayoutType(context),
                    sectoken = AppUtil.applicationToken,
                    lang = AppUtil.language,
                    deviceid = AppUtil.getDeviceId(context),
                    deviceType = NetworkUtils.getDeviceLayoutType(context),
                    sv = Constants.svc
                )

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