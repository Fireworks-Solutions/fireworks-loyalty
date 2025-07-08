package com.incredibleqr.mysogo.data.remote.model.reward

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class RewardResponse(

	@field:SerializedName("location")
	val location: List<LocationItem?>? = null,

	@field:SerializedName("custname")
	val custname: String? = null,

	@field:SerializedName("rewards")
	val rewards: ArrayList<RewardsItem>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("points")
	val points: String? = null
)