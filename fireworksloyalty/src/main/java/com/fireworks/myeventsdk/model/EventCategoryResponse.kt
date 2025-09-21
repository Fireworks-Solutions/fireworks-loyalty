package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class EventCategoryResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,


	@field:SerializedName("sort_by")
	val sort_by: ArrayList<SortFilter>? = null,


	@field:SerializedName("categoryid")
	val categoryid: ArrayList<CategoryFilter>? = null,



	)