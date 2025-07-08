package com.fireworks.myeventsdk.model.directory

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class DirectoryItem(

	@field:SerializedName("siteurl")
	val siteurl: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("floor")
	val floor: String? = null,

	@field:SerializedName("featured_img")
	val featuredImg: String? = null,

	@field:SerializedName("unit_no")
	val unitNo: String? = null,

	@field:SerializedName("floor_unit")
	val floorUnit: String? = null,

	@field:SerializedName("verified_merchant")
	val verified: String? = null
)