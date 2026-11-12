package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

/** Response of api/ecom-login.php (T-Marketplace login). */
data class EcomLoginResponse(

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
