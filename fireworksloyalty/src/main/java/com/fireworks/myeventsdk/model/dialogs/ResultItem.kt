package com.fireworks.myeventsdk.model.dialogs

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("close_type")
	val closeType: String? = null
)