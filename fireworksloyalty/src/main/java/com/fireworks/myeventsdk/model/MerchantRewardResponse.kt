package com.incredibleqr.mysogo.data.remote.model

import com.google.gson.annotations.SerializedName

data class MerchantRewardResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,


    @field:SerializedName("results")
    val resultMerchant: ArrayList<ResultMerchant>? = null,

)
