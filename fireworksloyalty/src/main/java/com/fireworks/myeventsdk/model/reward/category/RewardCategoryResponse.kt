package com.fireworks.myeventsdk.model.reward.category

import com.google.gson.annotations.SerializedName

data class RewardCategoryResponse(

    @field:SerializedName("category")
	val result: List<RewardCategory>? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("message")
		val message: String?= null
)