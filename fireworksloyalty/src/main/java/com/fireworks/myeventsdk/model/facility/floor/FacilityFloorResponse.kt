package com.fireworks.myeventsdk.model.facility.floor

import com.google.gson.annotations.SerializedName

data class FacilityFloorResponse(

    @field:SerializedName("results")
    val results: ArrayList<Floor>? = null
)
