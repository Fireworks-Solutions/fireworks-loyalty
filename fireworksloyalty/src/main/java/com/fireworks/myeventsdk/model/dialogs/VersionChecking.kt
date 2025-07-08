package com.incredibleqr.mysogo.data.remote.model.dialogs

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class VersionChecking(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("migration_enabled")
	val migrationEnabled: Boolean? = null
)