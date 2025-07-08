package com.incredibleqr.mysogo.data.remote.model.dailyReward

import com.google.gson.annotations.SerializedName

data class HistoryItem(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("check_in_at")
    val checkInAt: String? = null,

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("reward")
    val reward: String? = null,
)
