package com.fireworks.myeventsdk.model.reward.category

import com.google.gson.annotations.SerializedName

data class RewardCategory(

	@field:SerializedName("category_id")
	val id: String? = null,

	@field:SerializedName("category")
	val title: String? = null,

	@field:SerializedName("category_img")
	val featuredImg: String? = null
)



data class FilterData(
	val status: String,
	val message: String,
	val primary_filter: List<FilterOption>,
	val sort_by: List<SortOption>,
	val rewards_type: List<RewardsType>,
	val category: List<Category>
)

data class FilterOption(
	val title: String,
	val id: String,
	val image: String,
	val featured: Boolean
)

data class SortOption(
	val title: String,
	val id: String,
	val image: String,
	val featured: Boolean
)

data class RewardsType(
	val title: String,
	val id: String,
	val image: String,
	val featured: Boolean
)

data class Category(
	val id: Int,
	val title: String,
	val image: String,
	val featured_img: String,
	val featured: Boolean
)