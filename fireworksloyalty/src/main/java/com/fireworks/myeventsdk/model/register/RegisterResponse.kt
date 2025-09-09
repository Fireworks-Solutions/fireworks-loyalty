package com.fireworks.myeventsdk.model.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse (

        @field:SerializedName("status")
        val status: String,

        @field:SerializedName("message")
        val message: String,

        @field:SerializedName("custid")
        val custId: String,

        @field:SerializedName("phone")
        val phone: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("email")
        val email: String,

        @field:SerializedName("token")
        val token: String ,

        @field:SerializedName("member_id")
        val member_id: String


)