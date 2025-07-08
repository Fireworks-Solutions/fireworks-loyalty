package com.fireworks.myeventsdk.model.wallet

import com.google.gson.annotations.SerializedName

data class CheckMigrateUserResponse (

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("migrated_user")
    val migratedUser: Boolean? = null
)