package com.incredibleqr.mysogo.data.remote.model.dashboard

import com.google.gson.annotations.SerializedName
import com.fireworks.myeventsdk.model.news.NewsItem
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class DashboardResponse(

        @field:SerializedName("profile")
        val profile: String? = null,

        @field:SerializedName("got_profile")
        val gotProfile: String? = null,

        @field:SerializedName("custname")
        val custname: String? = null,

        @field:SerializedName("tenants")
        val tenants: ArrayList<TenantItem>? = null,

        @field:SerializedName("news")
        val news: ArrayList<NewsItem>? = null,

        @field:SerializedName("promotions")
        val promotions: ArrayList<NewsItem>? = null,

        @field:SerializedName("members_news")
        val members: ArrayList<NewsItem>? = null,

        @field:SerializedName("banner_news")
        val banners: ArrayList<NewsItem>? = null,

        @field:SerializedName("rewards")
        val rewards: ArrayList<RewardsItem>? = null,

        @field:SerializedName("events")
        val events: ArrayList<EventsItem>? = null,

        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("message")
        val message: String? = null,

        @field:SerializedName("points")
        val points: String? = null,

        @field:SerializedName("showDailyCheckIn")
        val showDailyRewards: Boolean? = null,

        @field:SerializedName("purchase_norating")
        val purchaseNoRating: ArrayList<PurchaseNoRatingItem>? = null
)