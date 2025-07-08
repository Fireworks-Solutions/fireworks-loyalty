package com.fireworks.myeventsdk.model.migratedUser

import com.google.gson.annotations.SerializedName

data class MigratedData(

    @field:SerializedName("fname")
    val fname: String? = null,

    @field:SerializedName("lname")
    val lname: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,
    @field:SerializedName("nric")
    val nric: String? = null,
    @field:SerializedName("dob")
    val dob: String? = null,
    @field:SerializedName("address1")
    val address1: String? = null,
    @field:SerializedName("address2")
    val address2: String? = null,
    @field:SerializedName("points")
    val points: Int? = null,
    @field:SerializedName("city")
    val city: String? = null,
    @field:SerializedName("postcode")
    val postcode: String? = null,

    @field:SerializedName("gender")
    val gender: Int? = null,
)
