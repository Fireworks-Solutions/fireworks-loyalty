package com.fireworks.myeventsdk.model.reward.detail

import com.fireworks.myeventsdk.model.wallet.detail.validityInfo
import com.google.gson.annotations.SerializedName
import com.incredibleqr.mysogo.data.remote.model.reward.detail.CollectionItem
import com.incredibleqr.mysogo.data.remote.model.reward.detail.MerchantItem

data class DetailsItem(

	@field:SerializedName("validity_message")
	val validityMessage: String? = null,

	@field:SerializedName("limit_message")
	val limitMessage: String? = null,

	@field:SerializedName("stock_status")
	val stockStatus: Int? = null,

	@field:SerializedName("purchase_end")
	val purchaseEnd: String? = null,

	@field:SerializedName("tnc")
	val tnc: String? = null,

	@field:SerializedName("branches_available")
	val branchesAvailable: Int? = null,


	@field:SerializedName("participant_outlet_count")
	val participant_outlet_count: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("purchase_limit")
	val purchaseLimit: String? = null,

	@field:SerializedName("is_premium")
	val is_premium: Boolean? = null,

	@field:SerializedName("validity_info")
	val validity_info: validityInfo? = null,

	@field:SerializedName("purchase_quantity")
	val purchaseQuantity: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("featured_image")
	val featuredImage: String? = null,

	@field:SerializedName("points")
	val points: String? = null,

	@field:SerializedName("points_raw")
	val pointsRaw: Int? = null,

	@field:SerializedName("merchant_name")
	val merchantName: String? = null,

	@field:SerializedName("more_details")
	val moreDetails: String? = null,

	@field:SerializedName("quantity_message")
	val quantityMessage: String? = null,

	@field:SerializedName("redeem_start")
	val redeemStart: String? = null,

	@field:SerializedName("merchantid")
	val merchantid: String? = null,

	@field:SerializedName("merchant_info")
	val merchantList: ArrayList<MerchantItem>? = null,

	@field:SerializedName("redeem_location")
	val redeemLocation: String? = null,

	@field:SerializedName("purchase_start")
	val purchaseStart: String? = null,

	@field:SerializedName("cash_purchase")
	val cashPurchase: Boolean? = null,

    @field:SerializedName("purchase_amount")
	val purchaseAmount: String? = null,

	@field:SerializedName("bookmark_status")
	val bookmarkStatus: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("validity")
	val validity: String? = null,

	@field:SerializedName("redeem_end")
	val redeemEnd: String? = null,

	@field:SerializedName("stock_message")
	val stockMessage: String? = null,

	@field:SerializedName("mall")
	val mall: Int? = null,

	@field:SerializedName("gift")
	val gift: Boolean? = null,

	@field:SerializedName("is_donation")
	val is_donation: Boolean? = null,

	@field:SerializedName("is_swipe")
	val isSwipe: Boolean? = null,

	@field:SerializedName("is_third_party")
	val is_third_party: Boolean? = null,

	@field:SerializedName("pickup")
	val pickup: Boolean? = null,

	@field:SerializedName("validity_days")
	val validity_days: String? = null,

	@field:SerializedName("delivery")
	val delivery: Boolean? = null,

	@field:SerializedName("has_dob")
	val has_dob: Boolean? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("third_party_redirect")
	val third_party_redirect: String? = null,


	@field:SerializedName("full_address")
	val fullAddress: String? = null,

	@field:SerializedName("collection_method")
	val collectionMethod: ArrayList<CollectionItem>? = null,

	@field:SerializedName("images")
	val Images: ArrayList<String>? = null
)

