package com.incredibleqr.mysogo.data.remote.model.reward.detail

import com.google.gson.annotations.SerializedName

data class CollectionItem(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("shipping")
    val status: Boolean? = null
)
