package com.incredibleqr.mysogo.data.remote.model.reward.detail

import com.google.gson.annotations.SerializedName

data class MerchantItem(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("mall")
    val mall: String? = null
)
