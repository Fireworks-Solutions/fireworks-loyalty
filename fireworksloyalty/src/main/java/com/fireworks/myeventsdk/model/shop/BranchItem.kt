package com.fireworks.myeventsdk.model.shop

import com.google.gson.annotations.SerializedName

data class BranchItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)