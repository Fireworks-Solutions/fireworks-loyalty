package com.incredibleqr.mysogo.data.remote.model.branch.states

import com.google.gson.annotations.SerializedName

data class StatesResponse (

        @field:SerializedName("status")
        val status: String,

        @field:SerializedName("results")
        val results: ArrayList<StateResult>
)