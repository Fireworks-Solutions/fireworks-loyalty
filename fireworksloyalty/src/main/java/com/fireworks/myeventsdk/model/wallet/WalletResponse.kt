package com.fireworks.myeventsdk.model.wallet

import com.google.gson.annotations.SerializedName
import com.incredibleqr.mysogo.data.remote.model.wallet.WalletItem


data class WalletResponse(

	@field:SerializedName("wallet")
	val wallet: ArrayList<WalletItem>? = null,

	@field:SerializedName("custname")
	val custname: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("points")
	val points: String? = null
)