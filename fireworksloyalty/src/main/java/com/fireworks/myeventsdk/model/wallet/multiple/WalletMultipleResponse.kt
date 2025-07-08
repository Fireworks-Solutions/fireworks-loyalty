package com.incredibleqr.mysogo.data.remote.model.wallet.multiple

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
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