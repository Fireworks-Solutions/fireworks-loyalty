package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class BaseUrlResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: DataUrls? = null,

)

data class DataUrls(

    @field:SerializedName("base_url")
    val baseUrl: String? = null,

    @field:SerializedName("marketplace_base_url")
    val marketPlaceBaseUrl: String? = null

)