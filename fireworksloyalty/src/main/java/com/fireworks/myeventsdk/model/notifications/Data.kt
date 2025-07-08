package com.fireworks.myeventsdk.model.notifications

import com.google.gson.annotations.SerializedName

data class Data (

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("unread")
    val unread: Int? = null,

    var isExpanded:Boolean = false,

    var archived: Boolean = false
)