package com.incredibleqr.mysogo.data.remote.model.dashboard

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

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
	val point: String
)