package com.fireworks.myeventsdk.model.reward.detail

import com.google.gson.annotations.SerializedName

data class RewardDetailResponse(

    @field:SerializedName("details")
	val details: List<DetailsItem?>? = null,

    @field:SerializedName("custname")
	val custname: String? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("message")
	val message: String? = null
)