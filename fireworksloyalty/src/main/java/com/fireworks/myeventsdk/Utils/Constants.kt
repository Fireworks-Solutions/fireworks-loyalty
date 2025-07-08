package com.fireworks.myeventsdk.Utils

object Constants {
    const val API_PATH = "api2"
    const val API1_PATH = "api"

    const val svc = "ami3jf8anasnd1f"
    /**
     * network
     */
    //LOGIN API
    const val LOGIN_API = "$API_PATH/login.php?"
    const val ADD_DEVICE_TOKEN_API = "$API_PATH/addDeviceToken.php?"
    const val CHECK_SESSION_API = "$API_PATH/checkSession.php"
    const val VERIFY_PASSWORD_API = "$API_PATH/get_verify_password.php?"

    //SIGN UP API
    const val CHECK_MIGRATED_USER_API = "$API_PATH/checkMigrateExist.php?"
    const val GET_MIGRATED_DATA_API = "$API_PATH/getMigratedUserData.php?"
    const val SEND_OTP_API = "$API_PATH/otp_register.php?"
    const val OTP_VERIFY_API = "$API_PATH/otp_register_verification.php?"
    const val SIGN_UP_API = "$API_PATH/register.php?"
    const val CHECK_EMAIL_API = "$API_PATH/checkEmail.php?"
    const val SIGN_UP_FRIEND_API = "$API_PATH/registerfr.php?"
    const val SIGN_UP_JUNIOR_API = "$API_PATH/registerjr.php?"

    //DASHBOARD API
    const val DASHBOARD_API = "$API_PATH/dashboard.php?"

    //USER INFO API
    const val PROFILE_PIC_API = "$API_PATH/getProfileImage.php?"
    const val TRANSFER_POINTS_URL = "$API_PATH/merchantAddPoints.php?"

    //NEWS API
    const val NEWS_API = "$API_PATH/getNews.php?"
    const val NEWS_DETAIL_API = "$API_PATH/getNewsDetails.php?"

    //REWARD API
    const val REWARD_API = "$API_PATH/webview_getRewards.php?"
    const val REWARD_DETAIL_API = "$API_PATH/webview_getDetails.php?"
    const val REWARD_CATEGORY_API = "$API_PATH/webview_getCategory.php?"
    const val REWARD_CHECKOUT_API = "$API_PATH/checkout.php?"
    const val GIFT_CHECKOUT_API = "$API_PATH/giftCheckout.php?"
    const val SHIPPING_POINTS_API = "$API_PATH/get_shipping_points.php?"
    const val ADD_REWARD_URL = "e-commerce/$API_PATH/addCart.php?"
    const val REWARD_WEBVIEW_URL = "ecom/promotion.php?"

    //EVENT API
    const val EVENT_API = "$API_PATH/webview_getEvents.php?"
    const val EVENT_DETAIL_API = "$API_PATH/webview_eventDetails.php?"
    const val EVENT_CHECKOUT_API = "$API_PATH/event_checkout.php?"
    const val ADD_EVENT_URL = "e-commerce/$API_PATH/addEventCart.php?"
    const val EVENT_WEBVIEW_URL = "ecom/events.php?"

    //WALLET API
    const val WALLET_API = "$API_PATH/webview_getWallet.php?"
    const val WALLET_MULTIPLE_API = "$API_PATH/webview_multiWalletList.php?"
    const val WALLET_DETAIL_API = "$API_PATH/webview_getWalletDetails.php?"
    const val USE_COUPON_URL = "ecom/qrvalidate.php?"
    const val QR_DETAIL_API = "$API_PATH/webview_getQrpage.php?"
    const val WALLET_SHARE_PEOPLE_API = "$API_PATH/custlist_dd.php?"
    const val WALLET_SHARE_API = "$API_PATH/share.php?"
    const val WALLET_WEBVIEW_URL = "ecom/wallet.php?"

    //Transaction History
    const val TRANSACTION_HISTORY_URL =
        "ecom/merchantTransactionHistory/merchantTransactionHistory.php?"
    const val TRANSFER_POINTS_HISTORY_URL =
        "ecom/merchantTransactionHistory/merchantPointHistory.php?"
    const val TRANSACTION_HISTORY_API = "$API_PATH/merchantTransactionHistoryNew.php?"
    const val ARCHIVE_TRANSACTION_API = "$API_PATH/archiveTransaction.php?"
    const val TRANSACTION_HISTORY_DETAIL_API = "$API_PATH/merchantTransactionHistorySingle.php?"
    const val GET_SETTINGS_API = "$API_PATH/getnoti.php?"
    const val UPDATE_SETTINGS_API = "$API_PATH/savenoti.php?"
    const val HOBBIES_INTERESTS_API = "$API_PATH/getInterestHobby.php?"

    //BOOKMARK API
    const val BOOKMARK_API = "$API_PATH/webview_bookmark.php?"

    //MERCHANT BRANCH LIST API
    const val MERCHANT_BRANCH_API = "$API_PATH/getMerchantBranchList.php?"
    const val BRANCH_API = "$API_PATH/webview_getBranches.php?"

    //DIRECTORY API
    const val DIRECTORY_CATEGORY_API = "$API_PATH/categories.php?"
    const val DIRECTORY_FLOOR_API = "$API_PATH/getDirectoryFloor.php?"
    const val DIRECTORY_API = "$API_PATH/getDirectoryList.php?"
    const val DIRECTORY_DETAIL_API = "$API_PATH/webview_aboutMerchant.php?"

    //FACILITY API
    const val FACILITY_CATEGORY_API = "$API_PATH/facility_categories.php?"
    const val FACILITY_FLOOR_API = "$API_PATH/getFacilityFloor.php?"
    const val FACILITY_API = "$API_PATH/getFacilities.php?"
    const val FACILITY_DETAIL_API = "$API_PATH/webview_aboutFacility.php?"

    //SUPPORT API
    //TODO : Need to amend 'subject' in api
    const val CREATE_TICKET_API = "information/support_mailer.php?"

    //ABOUT US
    const val ATRIA_TND_URL = "http://www.atria.com.my/atria/terms-of-use.html"
    const val ATRAI_PDPA_URL = "http://www.atria.com.my/atria/personal-data-protection-act.html"

    //PROFILE API
    const val PROFILE_API = "$API_PATH/profile.php?"
    const val UPDATE_PROFILE_API = "$API_PATH/updateProfile.php?"
    const val UPDATE_PROFILE_PIC_API = "$API_PATH/uploadProfilePic.php?"
    const val RESEND_ACTIVATION_LINK = "$API_PATH/resend_activation_link.php?"
    const val CHANGE_PASSWORD_API = "$API_PATH/changepass.php?"
    const val SET_PASSWORD_API = "$API_PATH/setPass.php?"
    const val MIGRATION_RESET_PASSWORD_API = "migration_resetpass_email_api.php?"
    const val FORGET_PASSWORD_API = "e-commerce/api/forgetPass2.php?"
    const val URL_SMS_RESET_OTP = "e-commerce/api/sms_reset_otp_verification.php?"
    const val URL_UPDATE_PASSWORD_FROM_SMS = "api/changepass.php?"

    //SHIPPING ADDRESS API
    const val CREATE_SHIPPING_ADDRESS_API = "$API_PATH/setShippingAddress.php?"
    const val EDIT_SHIPPING_ADDRESS_API = "$API_PATH/updateShippingAddress.php?"
    const val GET_SHIPPING_ADDRESS_API = "$API_PATH/getShippingAddress.php?"
    const val DELETE_SHIPPING_ADDRESS_API = "$API_PATH/deleteShippingAddress.php?"

    //NOTIFICATION API
    const val GET_NOTI_API = "$API_PATH/getnoti.php?"
    const val SAVE_NOTI_API = "$API_PATH/savenoti.php?"
    const val INBOX_ACTION_API = "$API_PATH/inbox_action.php?"
    const val INBOX_LISTING_API = "$API_PATH/inbox_listing.php?"

    //NEARBY REWARD API
    const val FEED_VERSION_API = "feed_version.php"
    const val GEOFENCE_API = "getResult2.php"
    const val GEOFENCE_DETAIL_API = "geofence_details.php"
    const val BEACON_API = "getResult.php"
    const val BEACON_DETAIL_API = "push_details.php"
    const val ACTION_BUTTON_ANALYTIC_API = "action_button_respond.php?"
    const val VERSION_DIALOG_CHECKING_API = "$API_PATH/alertList.php?"
    const val IN_APP_ALERT_API = "$API1_PATH/member_alertList.php?"
    const val REFRESH_TOKEN = "$API_PATH/refreshToken.php?"

    //SCAN RECEIPT API
    const val UPLOAD_IMAGE_API = "$API_PATH/uploadReceipt.php?"
    const val SCAN_RECEIPT_API = "$API_PATH/addReceipt.php?"

    //JEWEL BOX API
    const val JEWEL_CATEGORY_API = "$API_PATH/purchase_categories.php?"
    const val JEWEL_BOX_API = "$API_PATH/getJewelBox.php?"
    const val PURCHASE_RATING_API = "$API_PATH/purchaseRating.php?"
    const val TRANSFER_OWNERSHIP_API = "$API_PATH/transferPurchase.php?"

    const val BRANCH_STATES_API = "$API_PATH/branch_states.php?"
    const val BRANCH_AREAS_API = "$API_PATH/branch_city.php?"
    const val BRANCH_LOCATIONS_API = "$API_PATH/getBranches.php?"

    const val ADD_POINTS_API = "$API_PATH/giveRewardPoints.php?"
    const val COUNTRY_LIST_API = "$API_PATH/countryList.php?"
    const val HONORIFIC_LIST_API = "e-commerce/api/honorificList.php?"
    const val INCOME_INTEREST_API = "$API_PATH/registration_selection.php?"
    const val NOTIFICATION_HISTORY_API = "$API_PATH/getNotifications.php?"
    const val MALL_LIST_API = "$API_PATH/malllist.php?"
    const val POINT_DETAIL_API = "$API1_PATH/PointDetails.php?"
    const val USEFUL_LINKS_API = "$API_PATH/usefullinks.php?"
    const val MAKE_TOURIST_API = "$API_PATH/makeCustomerTourist.php?"
    const val REFERRAL_DATA_API = "$API1_PATH/getReferralRewardData.php?"
    const val CHECK_REFERRAL_API = "$API1_PATH/check_valid_referral.php?"
    const val RELEASE_POINTS_API = "$API_PATH/releasePoints.php?"

    const val DAILY_REWARD_API = "$API_PATH/get_daily_check_in_rewards.php?"
    const val CHECK_IN_API = "$API_PATH/daily_check_in_rewards.php?"
    const val UPDATE_PAYABLE_BY_POINTS = "$API1_PATH/updatePayableByPoints.php?"
    const val SURVEY_API = "$API1_PATH/view_survey_uncompleted.php?"
    const val COUNTRY_CODES_API = "$API1_PATH/getCountryCodes.php"

}