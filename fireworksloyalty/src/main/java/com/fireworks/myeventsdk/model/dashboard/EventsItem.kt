package com.fireworks.myeventsdk.model.dashboard

import com.google.gson.annotations.SerializedName

data class EventsItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("open_link")
	val openLink: Boolean? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("mall")
	val mall: String? = null,

	@field:SerializedName("point")
	val point: String? = null
)