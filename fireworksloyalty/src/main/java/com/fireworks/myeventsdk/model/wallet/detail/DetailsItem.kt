package com.fireworks.myeventsdk.model.wallet.detail

import com.google.gson.annotations.SerializedName
import com.incredibleqr.mysogo.data.remote.model.reward.detail.MerchantItem

data class DetailsItem(

        @field:SerializedName("validity_message")
        val validityMessage: String? = null,

        @field:SerializedName("purchase_end")
        val purchaseEnd: String? = null,

        @field:SerializedName("branches_available")
        val branchesAvailable: Int? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("gift_status")
        val giftStatus: String? = null,

        @field:SerializedName("featured_image")
        val featuredImage: String? = null,

        @field:SerializedName("points")
        val points: String? = null,

        @field:SerializedName("share_link")
        val shareLink: String? = null,

        @field:SerializedName("more_details")
        val moreDetails: String? = null,

        @field:SerializedName("wallet_id")
        val walletId: String? = null,

        @field:SerializedName("button_status")
        val buttonStatus: Int? = null,

        @field:SerializedName("redeem_start")
        val redeemStart: String? = null,

        @field:SerializedName("merchantid")
        val merchantid: String? = null,

        @field:SerializedName("merchant_name")
        val merchantName: String? = null,

        @field:SerializedName("merchant_info")
        val merchantList: ArrayList<MerchantItem>? = null,

        @field:SerializedName("redeem_location")
        val redeemLocation: String? = null,

        @field:SerializedName("purchase_start")
        val purchaseStart: String? = null,

        @field:SerializedName("bookmark_status")
        val bookmarkStatus: Int? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("validity")
        val validity: String? = null,

        @field:SerializedName("redeem_end")
        val redeemEnd: String? = null,

        @field:SerializedName("type")
        val type: String? = null,

        @field:SerializedName("qrcode")
        val qrCode: String? = null,

        @field:SerializedName("mall")
        val mall: Int? = null,

        @field:SerializedName("redeem_status")
        val redeemStatus: String? = null,

        @field:SerializedName("shipping")
        val shipping: Boolean? = null,

        @field:SerializedName("redemption_venue")
        val redemptionVenue: String? = null,

        @field:SerializedName("delivery_info")
        val deliveryInfo: String? = null,

        @field:SerializedName("shipping_status")
        val shippingStatus: String? = null,

        @field:SerializedName("tracking_info")
        val trackingInfo: String? = null,

        @field:SerializedName("qr_code")
        val showQRCODE: Boolean? = null,

        @field:SerializedName("bar_code")
        val showBARCODE: Boolean? = null,

        @field:SerializedName("text_code")
        val showTEXTCODE: Boolean? = null,

        @field:SerializedName("full_address")
        val full_address: String? = null
)