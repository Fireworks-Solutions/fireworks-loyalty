package com.fireworks.myeventsdk.model.reward

import com.google.gson.annotations.SerializedName

data class LocationItem(

	@field:SerializedName("location_name")
	val locationName: String? = null,

	@field:SerializedName("location_id")
	val locationId: String? = null
)