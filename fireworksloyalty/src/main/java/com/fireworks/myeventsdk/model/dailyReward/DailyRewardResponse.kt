package com.incredibleqr.mysogo.data.remote.model.dailyReward

import com.google.gson.annotations.SerializedName

data class DailyRewardResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("claimed_today")
    val claimedToday: Boolean? = null,

    @field:SerializedName("weeks")
    val weeks: ArrayList<WeekItem>? = null,

    @field:SerializedName("check_in_history")
    val checkInHistory: ArrayList<HistoryItem>? = null,
)
