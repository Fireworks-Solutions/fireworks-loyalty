package com.fireworks.myeventsdk.model.support

import com.google.gson.annotations.SerializedName

data class SupportResponse(

	@field:SerializedName("isSent")
	val isSent: Boolean? = null
)