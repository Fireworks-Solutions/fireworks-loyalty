package com.fireworks.myeventsdk.sdk

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.fireworks.myeventsdk.NetworkService.Service
import com.fireworks.myeventsdk.Utils.AppPreference
import com.fireworks.myeventsdk.Utils.AppUtil
import com.fireworks.myeventsdk.Utils.CommonInterface.ScanReceiptCallback
import com.fireworks.myeventsdk.Utils.CommonInterface.UploadImageCallback
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScanSdk {

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
    fun uploadImage(
        context: Context,
        custId: String,
        pic: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: UploadImageCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "custid" to custId,
            "pic" to pic,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "sectoken" to AppUtil.applicationToken,
            "svc" to Constants.svc
        )

        // Merge dynamic params from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.uploadImageAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Upload failed: ${response.code()}")
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
    fun scanReceipt(
        context: Context,
        mallId: String,
        custId: String,
        merchantId: String,
        price: String,
        receiptNum: String,
        picName: String,
        receiptDate: String,
        shop: String,
        extraParams: Map<String, String> = emptyMap(),
        callback: ScanReceiptCallback
    ) {
        if (!NetworkUtils.isInternetAvailable(context)) {
            callback.onFailure("No Internet Connection")
            return
        }

        val fields = mutableMapOf(
            "mall" to mallId,
            "custid" to custId,
            "merchantid" to merchantId,
            "price" to price,
            "c_receipt_id" to receiptNum,
            "c_receipt_date" to receiptDate,
            "c_amount" to price,
            "c_merchant" to shop,
            "pic_name" to picName,
            "date" to NetworkUtils.unixTimeStamp().toString(),
            "vc" to NetworkUtils.getVCKey(),
            "sectoken" to AppUtil.applicationToken,
            "svc" to Constants.svc
        )

        // Add any extra/dynamic fields from host app
        fields.putAll(extraParams)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.scanReceiptAPI(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure("Failed: ${response.code()} - ${response.message()}")
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