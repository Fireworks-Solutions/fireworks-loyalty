package com.fireworks.myeventsdk.model.facility.detail

import com.google.gson.annotations.SerializedName

data class FacilityDetails(

    @field:SerializedName("term_conditions")
    val termConditions: String? = null,

    @field:SerializedName("menu5_url")
    val menu5Url: String? = null,

    @field:SerializedName("direction")
    val direction: String? = null,

    @field:SerializedName("fav_icon")
    val favIcon: Any? = null,

    @field:SerializedName("facebook_url")
    val facebookUrl: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("banner")
    val banner: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("featured_image")
    val featuredImage: String? = null,

    @field:SerializedName("google_url")
    val googleUrl: String? = null,

    @field:SerializedName("instagram_url")
    val instagramUrl: String? = null,

    @field:SerializedName("weburl")
    val weburl: String? = null,

    @field:SerializedName("contact")
    val contact: String? = null,

    @field:SerializedName("faq")
    val faq: String? = null,

    @field:SerializedName("privacy_policy")
    val privacyPolicy: String? = null,

    @field:SerializedName("menu5")
    val menu5: String? = null,

    @field:SerializedName("tagline")
    val tagline: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("youtube_url")
    val youtubeUrl: String? = null,

    @field:SerializedName("twitter_url")
    val twitterUrl: String? = null,

    @field:SerializedName("floor_image")
    val floorImage: String? = null,

    @field:SerializedName("open_time")
    val openTime: String? = null,

    @field:SerializedName("close_time")
    val closeTime: String? = null,

    @field:SerializedName("verified_merchant")
    val verified: String? = null
)
