package com.fireworks.myeventsdk.model.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse (

        @field:SerializedName("status")
        val status: String,

        @field:SerializedName("message")
        val message: String,

        @field:SerializedName("custid")
        val custId: String
)