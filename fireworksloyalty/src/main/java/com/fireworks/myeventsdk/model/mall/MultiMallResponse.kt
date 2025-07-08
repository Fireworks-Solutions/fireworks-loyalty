package com.incredibleqr.mysogo.data.remote.model.mall

import com.google.gson.annotations.SerializedName

data class MultiMallResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("malls")
    val malls: ArrayList<MallItem>? = null
)
