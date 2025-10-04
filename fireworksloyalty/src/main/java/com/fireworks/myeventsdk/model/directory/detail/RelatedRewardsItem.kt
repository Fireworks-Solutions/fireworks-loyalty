package com.fireworks.myeventsdk.model.directory.detail

import com.fireworks.myeventsdk.model.wallet.detail.validityInfo
import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class RelatedRewardsItem(

	@field:SerializedName("buy_to")
	val buyTo: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("redeem_loc")
	val redeemLoc: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("bookmark_status")
	val bookmarkStatus: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("point")
	val point: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("validity_info")
	val validity_info: validityInfo? = null
)