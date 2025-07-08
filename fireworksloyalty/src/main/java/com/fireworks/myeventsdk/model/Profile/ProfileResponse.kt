package com.fireworks.myeventsdk.model.Profile

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class ProfileResponse(

	@field:SerializedName("profile")
	val profile: Profile? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)