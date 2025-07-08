package com.fireworks.myeventsdk.model.facility

import com.google.gson.annotations.SerializedName

data class FacilityItem(

    @field:SerializedName("siteurl")
    val siteurl: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("categories")
    val categories: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("floor")
    val floor: String? = null,

    @field:SerializedName("featured_img")
    val featuredImg: String? = null,

    @field:SerializedName("featured_icon")
    val featuredIcon: String? = null,

    @field:SerializedName("unit_no")
    val unitNo: String? = null,

    @field:SerializedName("floor_unit")
    val floorUnit: String? = null,

    @field:SerializedName("floor_name")
    val floorName: String? = null,

    @field:SerializedName("verified_merchant")
    val verified: String? = null
)
