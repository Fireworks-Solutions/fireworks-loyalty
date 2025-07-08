package com.fireworks.myeventsdk.model.news.detail

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class NewsDetailResponse(

	@field:SerializedName("news")
	val news: List<NewsItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)