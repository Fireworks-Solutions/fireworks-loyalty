package com.fireworks.myeventsdk.model.transaction

import android.util.Log
import com.google.gson.annotations.SerializedName

data class TransactionResponse(

    @field:SerializedName("result")
    val result: List<TransactionItem>? = null,

    @field:SerializedName("status")
    val status: String? = null
)

fun TransactionResponse.getTransactionHistory(): ArrayList<TransactionHistoryItem> {
    var array = ArrayList<TransactionHistoryItem>()
    if (result != null) {
        for (i in result) {
            Log.d("MC_", "Month is ${i.month}")
            array.add(TransactionHistoryItem(items = i.data!!, title = i.month ?: ""))
        }
    }
    return array
}