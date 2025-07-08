package com.fireworks.myeventsdk.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionItem(

	@field:SerializedName("month")
	val month: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)