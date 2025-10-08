package com.fireworks.myeventsdk.model.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("news")
	val news: ArrayList<NewsItem>? = null,

    @field:SerializedName("featured")
	val featured: ArrayList<NewsItem>? = null,

    @field:SerializedName("status")
	val status: String? = null
)