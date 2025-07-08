package com.incredibleqr.mysogo.data.remote.model.reward.category

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class RewardCategoryResponse(

        @field:SerializedName("category")
	val result: List<RewardCategory>? = null,

        @field:SerializedName("status")
	val status: String? = null,

		@field:SerializedName("message")
		val message: String?= null
)