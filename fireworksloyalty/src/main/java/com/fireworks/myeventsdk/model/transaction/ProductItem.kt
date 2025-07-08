package com.fireworks.myeventsdk.model.transaction

import com.google.gson.annotations.SerializedName

data class ProductItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("qty")
    val qty: String? = null,

    @field:SerializedName("price")
    val price: String? = null,
)
