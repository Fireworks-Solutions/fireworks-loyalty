package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class UploadImageResponse (

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("pic_name")
    val picName: String? = null,

    @field:SerializedName("merchant")
    val merchant: String? = null,

    @field:SerializedName("receipt_id")
    val receiptId: ArrayList<String>? = null,

    @field:SerializedName("receipt_date")
    val receiptDate: String? = null,

    @field:SerializedName("mall_ids")
    val mallIds: String? = null,

    @field:SerializedName("amount")
    val amount: String? = null
)