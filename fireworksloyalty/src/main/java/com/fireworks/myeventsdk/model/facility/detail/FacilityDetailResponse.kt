package com.fireworks.myeventsdk.model.facility.detail

import com.google.gson.annotations.SerializedName

data class FacilityDetailResponse(

    @field:SerializedName("facility_details")
    val facilityDetails: FacilityDetails? = null,

    @field:SerializedName("custname")
    val custname: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
