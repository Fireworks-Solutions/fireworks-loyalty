package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class getSSOUserInfo(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("phone_country")
	val phone_country: String? = null,

	@field:SerializedName("points")
	val points: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)
