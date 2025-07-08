package com.incredibleqr.mysogo.data.remote.model.dailyReward

import com.google.gson.annotations.SerializedName

data class DayItem(

    @field:SerializedName("day")
    val day: Int? = null,

    @field:SerializedName("day_title")
    val dayTitle: String? = null,

    @field:SerializedName("day_checked_in")
    val dayCheckedIn: Boolean? = null,

    @field:SerializedName("day_reward")
    val dayReward: String? = null,
)
