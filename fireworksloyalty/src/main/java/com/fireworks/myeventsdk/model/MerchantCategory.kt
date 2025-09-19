package com.fireworks.myeventsdk.model

import com.fireworks.myeventsdk.model.reward.category.FilterData
import com.fireworks.myeventsdk.model.shop.BranchItem
import com.google.gson.annotations.SerializedName

data class MerchantCategory(

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("primary_filter")
	val primary_filter: ArrayList<CategoryFilter>? = null,

    @field:SerializedName("sort_by")
	val sort_by: ArrayList<SortFilter>? = null,


    @field:SerializedName("state")
	val state: ArrayList<Statee>? = null,

    @field:SerializedName("categoryid")
	val categoryid: ArrayList<categoryid>? = null,

    @field:SerializedName("status")
	val status: String? = null
)


data class CategoryFilter(
    val title: String,
    val id: String,

    )

data class SortFilter(
    val title: String,
    val id: String,

    )

data class Statee(
    val name: String,
    val id: String,

    )

data class categoryid(
    val title: String,
    val id: String,

    )