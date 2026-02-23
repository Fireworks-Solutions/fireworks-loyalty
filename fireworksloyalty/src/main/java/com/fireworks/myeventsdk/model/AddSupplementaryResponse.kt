package com.fireworks.myeventsdk.model

import com.fireworks.myeventsdk.model.news.NewsItem
import com.google.gson.annotations.SerializedName

data class AddSupplementaryResponse (
    @SerializedName("status") val status: String? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("custid") val custid: String? = null,
    @SerializedName("promo") val promo: Boolean? = null,
    @SerializedName("member_id") val memberId: String? = null
)