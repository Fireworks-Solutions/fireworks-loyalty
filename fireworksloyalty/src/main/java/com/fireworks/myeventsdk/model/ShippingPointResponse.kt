package com.incredibleqr.mysogo.data.remote.model

import com.google.gson.annotations.SerializedName

data class ShippingPointResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("points")
    val points: Int? = null
)
