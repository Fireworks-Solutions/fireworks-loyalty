package com.fireworks.myeventsdk.model.directory.floor

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class Floor(

	@field:SerializedName("floor_name")
	val floorName: String? = null,

	@field:SerializedName("floor_id")
	val floorId: Int? = null,

	@field:SerializedName("floor_unit")
	val floorUnit: String? = null
)