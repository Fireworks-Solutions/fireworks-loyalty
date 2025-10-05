package com.fireworks.myeventsdk.model.reward

import com.fireworks.myeventsdk.model.reward.category.Category
import com.google.gson.annotations.SerializedName

data class RewardCategoryTypeResponse(

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("categories")
    val categories: List<Category?>? = null
)