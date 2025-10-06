package com.fireworks.myeventsdk.model.login


import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class LoginEmailResponse(

        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("message")
        val message: String? = null,

        @field:SerializedName("action")
        val action: String? = null,

        @field:SerializedName("custid")
        val custid: String? = null
)