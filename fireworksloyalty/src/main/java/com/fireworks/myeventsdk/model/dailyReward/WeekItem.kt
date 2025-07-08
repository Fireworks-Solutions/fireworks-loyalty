package com.incredibleqr.mysogo.data.remote.model.dailyReward

import com.google.gson.annotations.SerializedName

data class WeekItem(

    @field:SerializedName("days")
    val days: ArrayList<DayItem>? = null
)
