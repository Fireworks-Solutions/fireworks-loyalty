package com.fireworks.myeventsdk.model.dashboard

import com.incredibleqr.mysogo.data.remote.model.dashboard.LabelInfo

data class RewardsItem(
	val date: String,
	val description: String,
	val id: String,
	val img: String,
	val label: String,
	val label_info: LabelInfo?,
	val mall: String,
	val mall_name: String,
	val name: String,
	val point: String,
	val points_raw: Int,
	val cash: String,
	val cash_payment: Boolean,
	val is_donation: Boolean,
)