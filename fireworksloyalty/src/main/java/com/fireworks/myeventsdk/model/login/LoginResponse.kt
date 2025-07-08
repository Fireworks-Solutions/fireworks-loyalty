package com.fireworks.myeventsdk.model.login


import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class LoginResponse(

        @field:SerializedName("fname")
        val fname: String? = null,

        @field:SerializedName("message")
        val message: String? = null,

        @field:SerializedName("url")
        val url: String? = null,

        @field:SerializedName("sid")
        val sid: String? = null,

        @field:SerializedName("change_password")
        val changePassword: String? = null,

        @field:SerializedName("lname")
        val lname: String? = null,

        @field:SerializedName("phone")
        val phone: String? = null,

        @field:SerializedName("custid")
        val custid: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("email")
        val email: String? = null,

        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("group")
        val group: String? = null,

        @field:SerializedName("register")
        val register: String? = null,

        @field:SerializedName("token")
        val token: String? = null,

        @field:SerializedName("reset")
        val reset: String? = null
)