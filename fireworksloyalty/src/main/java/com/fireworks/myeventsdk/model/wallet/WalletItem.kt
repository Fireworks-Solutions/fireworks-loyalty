package com.incredibleqr.mysogo.data.remote.model.wallet

import com.google.gson.annotations.SerializedName
import com.incredibleqr.mysogo.data.remote.model.dashboard.LabelInfo
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
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

    @field:SerializedName("label_info")
    val label_info:LabelInfo?
)