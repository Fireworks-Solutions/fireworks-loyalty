package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class SupportType(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("types")
    val types: ArrayList<Typess>? = null,

)
data class Typess(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,
)