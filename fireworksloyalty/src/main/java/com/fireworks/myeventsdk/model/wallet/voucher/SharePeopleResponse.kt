package com.incredibleqr.mysogo.data.remote.model.wallet.voucher

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class SharePeopleResponse(

        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("result")
        val result: List<PeopleItem?>? = null
)