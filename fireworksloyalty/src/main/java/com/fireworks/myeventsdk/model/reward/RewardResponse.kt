package com.incredibleqr.mysogo.data.remote.model.reward

import com.fireworks.myeventsdk.model.reward.LocationItem
import com.fireworks.myeventsdk.model.reward.RewardsItem
import com.google.gson.annotations.SerializedName

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