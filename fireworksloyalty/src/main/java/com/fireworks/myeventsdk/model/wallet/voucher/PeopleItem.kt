package com.fireworks.myeventsdk.model.wallet.voucher

import com.google.gson.annotations.SerializedName

data class PeopleItem(

	@field:SerializedName("custid")
	val custid: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("nric")
	val nric: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)