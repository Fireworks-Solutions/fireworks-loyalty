package com.fireworks.myeventsdk.model.facility.floor

import com.google.gson.annotations.SerializedName

data class Floor(

    @field:SerializedName("floor_name")
    val floorName: String? = null,

    @field:SerializedName("floor_id")
    val floorId: Int? = null
)
