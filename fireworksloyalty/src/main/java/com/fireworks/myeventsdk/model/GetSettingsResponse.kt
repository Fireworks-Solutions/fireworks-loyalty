package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class GetSettingsResponse(

	@field:SerializedName("news")
	val news: String? = null,

	@field:SerializedName("general")
	val general: String? = null,

	@field:SerializedName("righthere")
	val righthere: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("setting")
	val setting: String? = null
)