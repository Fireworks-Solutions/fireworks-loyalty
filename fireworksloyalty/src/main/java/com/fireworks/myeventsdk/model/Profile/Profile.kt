package com.fireworks.myeventsdk.model.Profile

import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated

@Generated("com.robohorse.robopojogenerator")
data class Profile(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("fname")
    val fname: String? = null,

    @field:SerializedName("qrcode")
    val qrcode: String? = null,

    @field:SerializedName("voucher")
    val voucher: String? = null,

    @field:SerializedName("profile")
    val profile: String? = null,

    @field:SerializedName("postcode")
    val postcode: String? = null,

    @field:SerializedName("address1")
    val addressone: String? = null,

    @field:SerializedName("address2")
    val addresstwo: String? = null,

    @field:SerializedName("preferred_mall")
    val preferredMall: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("region")
    val state: String? = null,

    @field:SerializedName("got_profile")
    val gotProfile: String? = null,

    @field:SerializedName("display_name")
    val displayName: String? = null,

    @field:SerializedName("id_type")
    val idType: Int? = null,

    @field:SerializedName("nric")
    val nric: String? = null,

    @field:SerializedName("cardno")
    val cardno: String? = null,

    @field:SerializedName("cust_type")
    val custType: String? = null,

    @field:SerializedName("user_type")
    val userType: String? = null,

    @field:SerializedName("hobby")
    val hobby: String? = null,

    @field:SerializedName("interest")
    val interest: String? = null,

    @field:SerializedName("race")
    val race: String? = null,

    @field:SerializedName("dob")
    val dob: String? = null,

    @field:SerializedName("pid")
    val parentId: String? = null,

    @field:SerializedName("pname")
    val parentName: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("total_event_purchased")
    val totalEventPurchased: String? = null,

    @field:SerializedName("points")
    val points: String? = null,

    @field:SerializedName("redeemable_points")
    val pointsRedeem: String? = null,

    @field:SerializedName("total_reward")
    val totalReward: String? = null,

    @field:SerializedName("lname")
    val lname: String? = null,

    @field:SerializedName("expired")
    val expired: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("phone_country")
    val countryCode: String? = null,

    @field:SerializedName("customer_status")
    val customerStatus: String? = null,

    @field:SerializedName("custid")
    val custid: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("member_card")
    val rankImg: String? = null,

    @field:SerializedName("qrimage")
    val qrimage: String? = null,

    @field:SerializedName("rank")
    val rank: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("total_reward_purchased")
    val totalRewardPurchased: String? = null,

    @field:SerializedName("next_level")
    val nextLevel: String? = null,

    @field:SerializedName("next_level_percentage")
    val nextPercent: Int? = null,

    @field:SerializedName("purchase_value")
    val purchaseValue: String? = null,

    @field:SerializedName("p_value")
    val pValue: Double? = null,

    @field:SerializedName("formatted_p_value")
    val formattedValue: String? = null,

    @field:SerializedName("love_anniversary")
    val anniversary: String? = null,

    @field:SerializedName("nationality")
    val nationality: String? = null,

    @field:SerializedName("country_of_residence")
    val residence: String? = null,

    @field:SerializedName("next_rank")
    val nextRank: String? = null,

    @field:SerializedName("card_expiry")
    val cardExpiry: String? = null,

    @field:SerializedName("point_expiry_date")
    val pointExpiryDate: String? = null,

    @field:SerializedName("point_expiry_value")
    val pointExpiryValue: String? = null,

    @field:SerializedName("referral_code")
    val referralCode: String? = null,

    @field:SerializedName("referral_web_url")
    val referralUrl: String? = null,

    @field:SerializedName("household_income")
    val income: Int? = null,

    @field:SerializedName("selected_interests")
    val interests: String? = null,

    @field:SerializedName("password_saved")
    val passwordSaved: Boolean? = null,

    @field:SerializedName("expiry_points")
    val pointsExpiry: String? = null,

    @field:SerializedName("showDailyCheckIn")
    val showDailyRewards: Boolean? = null,

    @field:SerializedName("show_pay_button")
    val payableByPoints: Boolean = false,

    @field:SerializedName("countdown_timer")
    val countDownTimer: Int? = null,

    @field:SerializedName("payable_by_points")
    val possible_to_pay_by_points: Boolean? = false,

    @field:SerializedName("paypointsenabled")
    val isPayByPointsModuleEnabled: Boolean? = false,

    @field:SerializedName("phone_verified")
    val isPhoneVerified: Boolean = false,

    @field:SerializedName("phone_verified_error_message")
    val phoneVerificationErrorMessage: String? = null,

    )