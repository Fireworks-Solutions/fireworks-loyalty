package com.fireworks.myeventsdk.model.links

import com.google.gson.annotations.SerializedName

data class LinksItem(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("icon")
    val icon: String? = null
)
