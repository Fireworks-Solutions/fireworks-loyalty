package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class CommonResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("img")
	val img: String? = null
)

data class ApiResponse(
	val status: String,
	val message: String,
	val voucher: String,
	val result: ResultData
)

data class ResultData(
	val total_reward: String,
	val total_reward_purchased: String,
	val total_event_purchased: String
)