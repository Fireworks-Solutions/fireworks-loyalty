package com.fireworks.myeventsdk.model.wallet.voucher

import com.google.gson.annotations.SerializedName

data class SharePeopleResponse(

        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("result")
        val result: List<PeopleItem?>? = null
)