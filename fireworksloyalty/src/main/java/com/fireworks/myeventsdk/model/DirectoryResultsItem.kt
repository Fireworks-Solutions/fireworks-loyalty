package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class DirectoryResultsItem(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("siteurl")
    val siteurl: String? = null,

    @field:SerializedName("featured_img")
    val featuredImg: String? = null,

    @field:SerializedName("categories")
    val categories: String? = null,

    @field:SerializedName("floor")
    val floor: String? = null,

    @field:SerializedName("floor_title")
    val floorTitle: String? = null,

    @field:SerializedName("unit_no")
    val unitNo: String? = null,

    @field:SerializedName("keyword")
    val keyword: String? = null,

    @field:SerializedName("floor_unit")
    val floorUnit: String? = null,

    @field:SerializedName("logo")
    val logo: String? = null,

    @field:SerializedName("distance")
    val distance: String? = null,

    @field:SerializedName("amount")
    val amount: String? = null,

    @field:SerializedName("star")
    val star: String? = null,
)