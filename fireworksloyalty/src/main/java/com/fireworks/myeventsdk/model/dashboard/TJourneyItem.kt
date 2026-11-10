package com.fireworks.myeventsdk.model.dashboard

import com.google.gson.annotations.SerializedName

/** Item of the dashboard `tjourney_items` array. `type` is "voucher" or "news". */
data class TJourneyItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("point")
	val point: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("open_link")
	val openLink: Boolean? = null
)
