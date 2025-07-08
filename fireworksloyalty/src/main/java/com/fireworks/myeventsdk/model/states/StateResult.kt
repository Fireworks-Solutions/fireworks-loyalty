package com.incredibleqr.mysogo.data.remote.model.branch.states

import com.google.gson.annotations.SerializedName

data class StateResult (

        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("state")
        val state: String
)