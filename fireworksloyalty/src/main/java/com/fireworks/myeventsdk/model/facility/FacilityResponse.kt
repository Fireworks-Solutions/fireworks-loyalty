package com.fireworks.myeventsdk.model.facility

import com.google.gson.annotations.SerializedName

data class FacilityResponse(

    @field:SerializedName("results")
    val facilities: ArrayList<FacilityItem>? = null
)
