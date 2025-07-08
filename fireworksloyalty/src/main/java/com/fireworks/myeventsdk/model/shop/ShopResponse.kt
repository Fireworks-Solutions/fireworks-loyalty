package com.fireworks.myeventsdk.model.shop

import com.google.gson.annotations.SerializedName

data class ShopResponse(

    @field:SerializedName("userid")
	val userid: String? = null,

    @field:SerializedName("branch")
	val branch: ArrayList<BranchItem>? = null,

    @field:SerializedName("status")
	val status: String? = null
)