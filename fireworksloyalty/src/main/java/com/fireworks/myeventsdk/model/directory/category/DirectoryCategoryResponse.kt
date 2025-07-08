package com.fireworks.myeventsdk.model.directory.category

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class DirectoryCategoryResponse(

	@field:SerializedName("result")
	val result: List<ResultItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)