package com.fireworks.myeventsdk.model.dashboard

import com.google.gson.annotations.SerializedName

data class PurchaseNoRatingItem (

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("product_name")
        val productName: String? = null,

        @field:SerializedName("receipt_no")
        val receiptNo: String? = null,

        @field:SerializedName("certificate_number")
        val certificateNumber: String? = null,

        @field:SerializedName("price")
        val price: String? = null,

        @field:SerializedName("point")
        val point: String? = null,

        @field:SerializedName("purchase_date")
        val purchaseDate: String? = null,

        @field:SerializedName("location")
        val location: String? = null,

        @field:SerializedName("sales_person")
        val sales_person: String? = null,

        @field:SerializedName("rating")
        val rating: String? = null
)