package com.incredibleqr.mysogo.data.remote.model.dashboard

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class TenantItem(

        @field:SerializedName("id")
        var tenantId: String? = null,

        @field:SerializedName("featured_img")
        var tenantImage: String? = null,

        @field:SerializedName("description")
        var tenantDescription: String? = null,

        @field:SerializedName("title")
        var tenantName: String? = null,

        @field:SerializedName("location")
        var tenantLocation: String? = null,

        @field:SerializedName("created_at")
        var tenantCreatedDate: String? = null
)