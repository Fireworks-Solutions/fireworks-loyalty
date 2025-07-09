package com.fireworks.myeventsdk.model.dialogs

import com.google.gson.annotations.SerializedName

data class VersionChecking(

    @field:SerializedName("result")
	val result: List<ResultItem?>? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("migration_enabled")
	val migrationEnabled: Boolean? = null
)