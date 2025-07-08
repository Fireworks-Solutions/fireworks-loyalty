package com.fireworks.myeventsdk.model.country

import com.google.gson.annotations.SerializedName

data class CountryItem (

        @field:SerializedName("value")
        val value: String,

        @field:SerializedName("label")
        val label: String
)