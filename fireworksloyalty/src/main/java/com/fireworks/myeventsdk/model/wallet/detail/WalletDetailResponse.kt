package com.fireworks.myeventsdk.model.wallet.detail

import com.google.gson.annotations.SerializedName

data class WalletDetailResponse(

    @field:SerializedName("shared_message")
	val shareMessage: ShareMessage? = null,

    @field:SerializedName("details")
	val details: List<DetailsItem?>? = null,

    @field:SerializedName("custname")
	val custname: String? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("message")
	val message: String? = null
)