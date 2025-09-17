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

	@field:SerializedName("end_date_text")
	val end_date_text: String? = null,

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
	val label_info:LabelInfo? = null,

	@field:SerializedName("points_raw")
	val pointsraw: Int?= null,

	@field:SerializedName("cash")
	val cash: String?= null,

	@field:SerializedName("cash_payment")
	val cashpayment: Boolean?= null,

	@field:SerializedName("is_donation")
	val isdonation: Boolean?= null,

	@field:SerializedName("validity_days")
	val validityDays: Boolean?= null
)