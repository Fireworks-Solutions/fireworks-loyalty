package com.fireworks.myeventsdk.model

import com.fireworks.myeventsdk.model.news.NewsItem
import com.google.gson.annotations.SerializedName

data class SupplimentaryProfileDetailResponse(
    val status: String? = null,
    val voucher: String? = null,
    val profile: SupplementaryProfile? = null
)

data class SupplementaryProfile(
    val customer_status: String? = null,
    val custid: String? = null,
    val title: String? = null,

    val name: String? = null,
    val fname: String? = null,
    val lname: String? = null,

    val email: String? = null,
    val nric: String? = null,

    val phone_country: String? = null,
    val display_name: String? = null,
    val phone: String? = null,
    val postcode: String? = null,

    val phone_verified: Boolean? = null,
    val phone_verified_error_message: String? = null,

    val cardno: String? = null,
    val hobby: String? = null,
    val interest: String? = null,
    val dob: String? = null,
    val race: String? = null,
    val gender: String? = null,

    val qrcode: String? = null,
    val qrimage: String? = null,

    val total_reward_purchased: String? = null,
    val total_event_purchased: String? = null,

    val redeemable_points: String? = null,
    val floating_points: String? = null,
    val points: String? = null,
    val expired: String? = null,

    val voucher: String? = null,

    val rank: String? = null,
    val cust_type: String? = null,
    val next_rank: String? = null,

    val rank_img: String? = null,
    val member_card: String? = null,

    val next_level: String? = null,
    val next_level_percentage: Int? = null,

    val pc: String? = null,
    val purchase_value: String? = null,
    val p_value: Int? = null,
    val formatted_p_value: String? = null,

    val user_type: String? = null,
    val preferred_mall: String? = null,

    val got_profile: String? = null,
    val profile: String? = null,

    val love_anniversary: String? = null,
    val nationality: String? = null,
    val country_of_residence: String? = null,

    val address1: String? = null,
    val address2: String? = null,
    val city: String? = null,
    val region: String? = null,

    val household_income: Int? = null,
    val selected_interests: String? = null,

    val created_date: String? = null,
    val card_expiry: String? = null,
    val is_tester: String? = null,

    val id_type: Int? = null,

    val referral_code: String? = null,
    val referral_web_url: String? = null,

    val expiry_points: String? = null,
    val point_expiry_date: String? = null,
    val point_expiry_value: String? = null,

    val points_expiry: List<PointExpiry>? = null,

    val all_point_expiry_value: String? = null,
    val is_verified: Int? = null,

    val paypointsenabled: Boolean? = null,
    val old_card_no: String? = null,
    val showDailyCheckIn: Boolean? = null,
    val payable_by_points: Boolean? = null,
    val show_pay_button: Boolean? = null,

    val countdown_timer: String? = null,
    val password_saved: Boolean? = null,
    val member_since: String? = null
)

data class PointExpiry(
    val expiry_points: Int? = null,
    val expiry_text: String? = null,
    val expiry_date: String? = null,
    val expiry_content: String? = null
)