package com.incredibleqr.mysogo.data.remote.model

import com.google.gson.annotations.SerializedName

data class ReferralDataResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("referer")
    val referer: String? = null,

    @field:SerializedName("referee")
    val referee: String? = null,

    @field:SerializedName("share_message")
    val shareMessage: String? = null
)
