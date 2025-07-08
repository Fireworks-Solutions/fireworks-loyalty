package com.incredibleqr.mysogo.data.remote.model.mall

import com.google.gson.annotations.SerializedName

data class MallItem(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("short_desc")
    val shortDesc: String? = null,

    @field:SerializedName("logo")
    val logo: String? = null,

    @field:SerializedName("text_logo")
    val textLogo: String? = null,

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("mall_icon_white")
    val inverseIcon: String? = null,

    @field:SerializedName("mall_logo_inverse")
    val inverseLogo: String? = null,

    @field:SerializedName("about")
    val about: String? = null,

    @field:SerializedName("contact_us")
    val contactUs: String? = null,

    @field:SerializedName("waze")
    val waze: String? = null,

    @field:SerializedName("whatsapp")
    val whatsapp: String? = null,

    @field:SerializedName("google_maps")
    val googleMaps: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("showDirectory")
    val showDirectory: Boolean? = null,

    @field:SerializedName("defaultMall")
    val defaultMall: Boolean? = null,

    @field:SerializedName("showReceipt")
    val showReceipt: Boolean? = null
)
