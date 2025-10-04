package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class MerchantRewardResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,


    @field:SerializedName("results")
    val resultMerchant: ArrayList<ResultMerchant>? = null,

)

data class ResultMerchant(
    val id: String? = null,
    val title: String? = null,
    val siteurl: String? = null,
    val featured_img: String? = null,
    val floor_img: String? = null,
    val logo: String? = null,
    val categories: String? = null,
    val floor: String? = null,
    val floor_title: String? = null,
    val unit_no: String? = null,
    val keyword: String? = null,
    val address: String? = null,
    val contact: String? = null,
    val floor_unit: String? = null,
    val total_voucher: String? = null,
    val distance: String? = null,
    val address_google_map: String? = null,
)
