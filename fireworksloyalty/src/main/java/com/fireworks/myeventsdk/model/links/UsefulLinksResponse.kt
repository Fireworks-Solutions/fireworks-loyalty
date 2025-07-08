package com.fireworks.myeventsdk.model.links

import com.google.gson.annotations.SerializedName

data class UsefulLinksResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("urls")
    val urls: ArrayList<LinksItem>? = null
)
