package com.fireworks.myeventsdk.model.login


import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class LoginEmailSSOResponse(

        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("message")
        val message: String? = null,

        @field:SerializedName("action")
        val action: String? = null,

        @field:SerializedName("custid")
        val custid: String? = null,

        @field:SerializedName("in_loyalty")
        val inLoyalty: Boolean? = null,

        @field:SerializedName("in_sso")
        val inSSO: Boolean? = null
)