package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

 @Generated("com.robohorse.robopojogenerator")
data class VerifyOtpResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("custid")
    val custid: String? = null
)