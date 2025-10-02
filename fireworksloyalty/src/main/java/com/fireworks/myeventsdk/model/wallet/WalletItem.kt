package com.fireworks.myeventsdk.model.wallet

import com.fireworks.myeventsdk.model.wallet.detail.validityInfo
import com.google.gson.annotations.SerializedName
import com.incredibleqr.mysogo.data.remote.model.dashboard.LabelInfo

data class WalletItem(

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("cid")
    val cid: String? = null,

    @field:SerializedName("units")
    val units: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("point")
    val point: String? = null,

    @field:SerializedName("merchant_name")
    val merchantName: String? = null,

    @field:SerializedName("mall")
    val mall: Int? = null,

    @field:SerializedName("expired_date")
    val expiredDate: String? = null,

    @field:SerializedName("label")
    val label: String? = null,

    @field:SerializedName("status")
    val itemStatus: String? = "",

    @field:SerializedName("is_premium")
    val is_premium: Boolean? = "",

    @field:SerializedName("validity_info")
    val validity_info: validityInfo? = "",

    @field:SerializedName("validity_days_message")
    val validity_days_message: String? = "",

    @field:SerializedName("label_info")
    val label_info:LabelInfo?
)