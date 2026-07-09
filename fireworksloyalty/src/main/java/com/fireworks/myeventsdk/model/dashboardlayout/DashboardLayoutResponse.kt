package com.fireworks.myeventsdk.model.dashboardlayout

import com.google.gson.annotations.SerializedName

data class DashboardLayoutResponse(
    @field:SerializedName("data")
    val data: DashboardLayoutData? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class DashboardLayoutData(
    @field:SerializedName("sections")
    val sections: ArrayList<DashboardLayoutSection>? = null,

    @field:SerializedName("banners")
    val banners: ArrayList<DashboardLayoutBanner>? = null
)

data class DashboardLayoutSection(
    @field:SerializedName("key")
    val key: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("order")
    val order: Int? = null,

    @field:SerializedName("enabled")
    val enabled: Boolean? = null,

    @field:SerializedName("rows")
    val rows: Int? = null,

    @field:SerializedName("cols")
    val cols: Int? = null,

    @field:SerializedName("max_items")
    val maxItems: Int? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("redirect_url")
    val redirectUrl: String? = null,

    @field:SerializedName("children")
    val children: ArrayList<DashboardLayoutQuickLink>? = null
)

data class DashboardLayoutQuickLink(
    @field:SerializedName("key")
    val key: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("order")
    val order: Int? = null,

    @field:SerializedName("enabled")
    val enabled: Boolean? = null,

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("redirect_url")
    val redirectUrl: String? = null,

    @field:SerializedName("image")
    val image: String? = null
)

data class DashboardLayoutBanner(
    @field:SerializedName("key")
    val key: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("order")
    val order: Int? = null,

    @field:SerializedName("enabled")
    val enabled: Boolean? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("redirect_url")
    val redirectUrl: String? = null
)
