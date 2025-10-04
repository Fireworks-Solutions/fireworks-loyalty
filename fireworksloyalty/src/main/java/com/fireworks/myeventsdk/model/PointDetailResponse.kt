package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class PointDetailResponse(

    @field:SerializedName("status")
    val status: String? = null,


    @field:SerializedName("register")
    val register: String? = null,

    @field:SerializedName("completeprofile")
    val completeprofile: String? = null
)
