package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class TimerResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("remaining_time")
	val remaining_time: Int? = null
)

