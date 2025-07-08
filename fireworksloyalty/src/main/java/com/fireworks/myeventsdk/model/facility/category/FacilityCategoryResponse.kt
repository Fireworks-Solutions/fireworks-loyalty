package com.fireworks.myeventsdk.model.facility.category

import com.google.gson.annotations.SerializedName

data class FacilityCategoryResponse(

    @field:SerializedName("result")
    val result: List<ResultItem>? = null,

    @field:SerializedName("status")
    val status: String? = null
)
