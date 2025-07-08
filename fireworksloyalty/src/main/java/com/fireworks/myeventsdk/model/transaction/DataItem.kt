package com.fireworks.myeventsdk.model.transaction

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("date2")
	var date: String? = null,

	@field:SerializedName("year")
	val year: String? = null,

	@field:SerializedName("m_y")
	val mY: String? = null,

	@field:SerializedName("cash_spent")
	val cashSpent: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("point_spent")
	val pointSpent: String? = null,

	@field:SerializedName("points")
	var points: String? = null,

	@field:SerializedName("points_label")
	var points_label: String? = null,

	@field:SerializedName("month")
	val month: String? = null,

	@field:SerializedName("custid")
	val custid: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("purchase_value")
	var purchaseValue: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("transaction_id")
	val transactionId: String? = null,

	@field:SerializedName("reason")
	val reason: String? = null,

	@field:SerializedName("custname")
	val custname: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Long? = null,

	@field:SerializedName("status")
	val status: String? = null,

	var isExpanded: Boolean = false,

	var archived: Boolean = false
)