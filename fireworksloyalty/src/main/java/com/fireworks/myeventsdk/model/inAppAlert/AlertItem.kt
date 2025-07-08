package com.incredibleqr.mysogo.data.remote.model.inAppAlert

import com.google.gson.annotations.SerializedName

data class AlertItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("close_type")
    val closeType: String? = null,

    @field:SerializedName("to_be_executed")
    val toBeExecuted: Boolean? = null
)
