package com.fireworks.myeventsdk.model.directory.detail

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class DirectoryDetailResponse(

	@field:SerializedName("merchant_details")
	val merchantDetails: MerchantDetails? = null,

	@field:SerializedName("tab")
	val tab: List<Any?>? = null,

	@field:SerializedName("branch_details")
	val branchDetails: List<BranchDetailsItem?>? = null,

	@field:SerializedName("related_rewards")
	val relatedRewards: List<RelatedRewardsItem>? = null,

	@field:SerializedName("custname")
	val custname: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)