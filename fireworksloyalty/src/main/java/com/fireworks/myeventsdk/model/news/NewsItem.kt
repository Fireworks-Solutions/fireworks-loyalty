package com.fireworks.myeventsdk.model.news

import com.google.gson.annotations.SerializedName

data class NewsItem(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("open_link")
	val openLink: Boolean? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("featured_img")
	val featuredImg: String? = null,

	@field:SerializedName("mall")
	val mall: Int? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
)