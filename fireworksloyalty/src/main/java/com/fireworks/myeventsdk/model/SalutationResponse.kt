package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class SalutationResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("results")
    val results: ArrayList<Resultss>? = null,

)
data class Resultss(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,
)