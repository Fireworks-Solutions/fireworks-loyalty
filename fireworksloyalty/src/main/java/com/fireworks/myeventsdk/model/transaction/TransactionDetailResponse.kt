package com.fireworks.myeventsdk.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionDetailResponse(


    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("custid")
    val custid: String? = null,

    @field:SerializedName("cash_spent")
    val cashSpent: String? = null,

    @field:SerializedName("custname")
    val custname: String? = null,

    @field:SerializedName("point_spent")
    val pointSpent: String? = null,

    @field:SerializedName("purchase_value")
    val purchaseValue: String? = null,

    @field:SerializedName("points")
    val points: String? = null,

    @field:SerializedName("points_label")
    val points_label: String? = null,

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("appeal")
    val appeal: String? = null,

    @field:SerializedName("remarks")
    val remarks: String? = null,

    @field:SerializedName("receipt_no")
    val receiptNo: String? = null,

    @field:SerializedName("points_release")
    val pointsRelease: Boolean? = null,

    @field:SerializedName("products")
    val products: ArrayList<ProductItem>? = null,

    @field:SerializedName("reason")
    val reason: String? = null,

    @field:SerializedName("type")
    val type: String? = "",

    @field:SerializedName("mall")
    val mall_id: String? = "",

    @field:SerializedName("mall_image")
    val mall_image: String? = "",

    @field:SerializedName("barcode")
    val barcode: String? = "",

    @field:SerializedName("total")
    val total: List<Total> = listOf(),

    @field:SerializedName("tender_type")
    val tenderType: List<TenderType> = listOf(),

    @field:SerializedName("staff")
    val staff: String? = null,

    @field:SerializedName("staff_id")
    val staff_id: String? = null,

    @field:SerializedName("trans")
    val trans: String? = null,

    @field:SerializedName("internalStaff")
    val internal_stuff: InternalStaff,

    @field:SerializedName("total_savings")
    val total_saving: String = "",

    @field:SerializedName("number_of_items")
    val number_of_items: Int = 0,

    @field:SerializedName("posted_date")
    val posted_date: String = "",

    @field:SerializedName("is_refund")
    val isRefund: Boolean = false,

    @field:SerializedName("refund_ref_no")
    val refundNumber: String,

    @field:SerializedName("total_transaction")
    val total_transaction: String,

    @field:SerializedName("transaction_type")
    val transaction_type: String? = "",

    @field:SerializedName("action_type")
    val actionType:String? = ""


)