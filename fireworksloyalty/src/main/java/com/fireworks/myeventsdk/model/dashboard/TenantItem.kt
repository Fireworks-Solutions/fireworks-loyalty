package com.fireworks.myeventsdk.model.dashboard

import com.google.gson.annotations.SerializedName

data class TenantItem(


        @field:SerializedName("id")
        var tenantId: String? = null,

        @field:SerializedName("featured_img")
        var tenantImage: String? = null,

        @field:SerializedName("description")
        var tenantDescription: String? = null,

        @field:SerializedName("title")
        var tenantName: String? = null,

        @field:SerializedName("logo")
        var logo: String? = null,

        @field:SerializedName("location")
        var tenantLocation: String? = null,

        @field:SerializedName("created_at")
        var tenantCreatedDate: String? = null,

        @field:SerializedName("total_reward")
        var totalReward: Int? = null
)