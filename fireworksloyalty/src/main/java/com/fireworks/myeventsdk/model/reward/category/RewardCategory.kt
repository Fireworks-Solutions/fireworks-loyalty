package com.incredibleqr.mysogo.data.remote.model.reward.category

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class RewardCategory(

	@field:SerializedName("category_id")
	val id: String? = null,

	@field:SerializedName("category")
	val title: String? = null,

	@field:SerializedName("category_img")
	val featuredImg: String? = null
)