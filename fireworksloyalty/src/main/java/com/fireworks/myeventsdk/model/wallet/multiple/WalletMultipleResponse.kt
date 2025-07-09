package com.fireworks.myeventsdk.model.wallet.multiple

import com.google.gson.annotations.SerializedName

data class WalletMultipleResponse(

    @field:SerializedName("wallet")
	val wallet: List<WalletItem>? = null,

    @field:SerializedName("custname")
	val custname: String? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("points")
	val points: String? = null
)