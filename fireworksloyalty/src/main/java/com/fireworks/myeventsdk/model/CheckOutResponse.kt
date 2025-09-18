package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class CheckOutResponse(

	@field:SerializedName("points_spent")
	val pointsSpent: Int? = null,

	@field:SerializedName("balance")
	val balance: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("redemption_id")
	val redemption_id: String? = null,

	@field:SerializedName("rsvp_id")
	val rsvp_id: String? = null,

	@field:SerializedName("countdown_timer")
	val countdown_timer: Int? = null,

	@field:SerializedName("validity_period")
	val validityPeriod: String? = null,

	@field:SerializedName("walletid")
	val walletid: String? = null,

	@field:SerializedName("wallettype")
	val wallettype: String? = null,


)