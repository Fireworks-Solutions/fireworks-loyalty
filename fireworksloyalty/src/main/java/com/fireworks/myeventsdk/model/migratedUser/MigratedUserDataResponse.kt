package com.fireworks.myeventsdk.model.migratedUser

import com.google.gson.annotations.SerializedName

data class MigratedUserDataResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("migrated_data")
    val migratedData: MigratedData? = null
)
