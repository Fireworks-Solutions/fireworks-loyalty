package com.incredibleqr.mysogo.data.remote.model.inAppAlert

import com.google.gson.annotations.SerializedName

data class InAppAlertResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("in_app_alerts")
    val alerts: ArrayList<AlertItem>? = null
)
