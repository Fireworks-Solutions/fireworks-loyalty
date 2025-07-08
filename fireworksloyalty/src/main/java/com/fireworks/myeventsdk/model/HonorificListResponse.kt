package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class HonorificListResponse (

        @field:SerializedName("status")
        val status: String,

        @field:SerializedName("message")
        val message: String,

        @field:SerializedName("honorifics")
        val titles: ArrayList<String>,

        @field:SerializedName("default")
        val default: String
)