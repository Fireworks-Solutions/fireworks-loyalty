package com.fireworks.myeventsdk.model.reward

import com.google.gson.annotations.SerializedName
import com.incredibleqr.mysogo.data.remote.model.dashboard.LabelInfo

data class RewardsItem(

	@field:SerializedName("buy_to")
	val buyTo: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("quantity")
	val quantity: String? = null,

	@field:SerializedName("redeem_loc")
	val redeemLoc: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("bookmark_status")
	val bookmarkStatus: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("merchant_name")
	val merchantName: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("point")
	val point: String? = null,

	@field:SerializedName("mall")
	val mall: Int? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("label_info")
	val label_info:LabelInfo?
)