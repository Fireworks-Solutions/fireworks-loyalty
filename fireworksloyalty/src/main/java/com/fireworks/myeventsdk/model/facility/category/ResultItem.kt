package com.fireworks.myeventsdk.model.facility.category

import com.google.gson.annotations.SerializedName

data class ResultItem(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("featured_img")
    val featuredImg: String? = null
)
