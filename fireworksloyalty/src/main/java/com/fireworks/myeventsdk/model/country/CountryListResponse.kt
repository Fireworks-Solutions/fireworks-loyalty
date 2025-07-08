package com.fireworks.myeventsdk.model.country

import com.google.gson.annotations.SerializedName

data class CountryListResponse (

        @field:SerializedName("result")
        val result: ArrayList<CountryItem>
)