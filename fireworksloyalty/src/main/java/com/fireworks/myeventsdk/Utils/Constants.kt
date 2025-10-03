package com.fireworks.myeventsdk.Utils

object Constants {
    const val API_PATH = "api2"
    const val API1_PATH = "api"

    const val svc = "ami3jf8anasnd1f"
    /**
     * network
     */
    //LOGIN API
    const val LOGIN_API = "login.php?"
    const val LOGIN_OTP_API = "loginOtp.php?"
    const val loginV2_with_otp = "loginV2.php?"

    const val ADD_DEVICE_TOKEN_API = "addDeviceToken.php?"
    const val CHECK_SESSION_API = "checkSession.php"
    const val VERIFY_PASSWORD_API = "get_verify_password.php?"
    const val DELETE_USER = "deleteCust.php?"
    const val CHECK_MOBILENUMBER = "checkMobileNumber.php"





    //SIGN UP API
    const val CHECK_MIGRATED_USER_API = "checkMigrateExist.php?"
    const val GET_MIGRATED_DATA_API = "getMigratedUserData.php?"
    const val SEND_OTP_API = "otp_register.php?"
    const val OTP_VERIFY_API = "otp_register_verification.php?"
    const val SIGN_UP_API = "register.php?"
    const val REGISTER_API = "registerV2.php"
    const val CHECK_EMAIL_API = "checkEmail.php?"
    const val SIGN_UP_FRIEND_API = "/registerfr.php?"
    const val SIGN_UP_JUNIOR_API = "/registerjr.php?"

    //DASHBOARD API
    const val DASHBOARD_API = "dashboard.php?"
    const val PRIVILEGE_DASH = "privileges_dashboard.php?"
    const val PRIVILEGE_RANK = "getRankInfo.php"

    //USER INFO API
    const val PROFILE_PIC_API = "getProfileImage.php?"
    const val TRANSFER_POINTS_URL = "merchantAddPoints.php?"

    //NEWS API
    const val NEWS_API = "getNews.php?"
    const val NEWS_DETAIL_API = "getNewsDetails.php?"

    //REWARD API
    const val REWARD_API = "webview_getRewards.php?"
    const val NEW_REWARDS = "webview_getRewardsV2.php?"
    const val PURCHASED_COUNT = "purchased_count.php?"
    const val REWARD_CATEGORIES = "categoriesV2.php?"
    const val REWARD_DETAIL_API = "webview_getDetails.php?"
    const val REWARD_CATEGORY_API = "webview_getCategory.php?"
    const val REWARD_CHECKOUT_API = "checkout.php?"
    const val TIMER_REWARD = "timer_reward.php?"
    const val GIFT_CHECKOUT_API = "giftCheckout.php?"
    const val SHIPPING_POINTS_API = "get_shipping_points.php?"
    const val ADD_REWARD_URL = "e-commerce//addCart.php?"
    const val REWARD_WEBVIEW_URL = "ecom/promotion.php?"
    const val DIRECTORY_LIST_REWARD = "getDirectoryListReward.php?"
    const val GET_SALUTATION = "get_salutation.php"
    const val SUPPORT_TYPE = "support_type.php"

    //EVENT API
    const val EVENT_API = "webview_getEvents.php?"
    const val FAVOURITE_API = "subNews.php?"
    const val EVENT_DETAIL_API = "webview_eventDetails.php?"
    const val EVENT_CHECKOUT_API = "event_checkout.php?"
    const val EVENT_CATEGORIES_API = "event_categories.php?"
    const val ADD_EVENT_URL = "e-commerceaddEventCart.php?"
    const val EVENT_WEBVIEW_URL = "ecomevents.php?"

//    WALLET API
    const val WALLET_API = "webview_getWallet.php?"
    const val WALLET_MULTIPLE_API = "webview_multiWalletList.php?"
    const val WALLET_DETAIL_API = "webview_getWalletDetails.php?"
    const val USE_COUPON_URL = "ecom/qrvalidate.php?"
    const val QR_DETAIL_API = "/webview_getQrpage.php?"
    const val WALLET_SHARE_PEOPLE_API = "/custlist_dd.php?"
    const val WALLET_SHARE_API = "/share.php?"
    const val WALLET_WEBVIEW_URL = "ecom/wallet.php?"

    //Transaction History
    const val TRANSACTION_HISTORY_URL =
        "ecom/merchantTransactionHistory/merchantTransactionHistory.php?"
    const val TRANSFER_POINTS_HISTORY_URL =
        "ecom/merchantTransactionHistory/merchantPointHistory.php?"
    const val TRANSACTION_HISTORY_API = "merchantTransactionHistoryNew.php?"
    const val ARCHIVE_TRANSACTION_API = "archiveTransaction.php?"
    const val TRANSACTION_HISTORY_DETAIL_API = "merchantTransactionHistorySingle.php?"
    const val GET_SETTINGS_API = "getnoti.php?"
    const val UPDATE_SETTINGS_API = "savenoti.php?"
    const val CLEAR_ALL_NOTIFICATION = "clear_all_noti.php?"
    const val HOBBIES_INTERESTS_API = "/getInterestHobby.php?"

    //BOOKMARK API
    const val BOOKMARK_API = "/webview_bookmark.php?"

    //MERCHANT BRANCH LIST API
    const val MERCHANT_BRANCH_API = "getMerchantBranchList.php?"
    const val MERCHANT_CATEGORIES = "merchant_categories.php?"
    const val BRANCH_API = "webview_getBranches.php?"

    //DIRECTORY API
    const val DIRECTORY_CATEGORY_API = "categories.php?"
    const val DIRECTORY_FLOOR_API = "getDirectoryFloor.php?"
    const val DIRECTORY_API = "getDirectoryList.php?"
    const val DIRECTORY_DETAIL_API = "webview_aboutMerchant.php?"

    //FACILITY API
    const val FACILITY_CATEGORY_API = "facility_categories.php?"
    const val FACILITY_FLOOR_API = "getFacilityFloor.php?"
    const val FACILITY_API = "getFacilities.php?"
    const val FACILITY_DETAIL_API = "webview_aboutFacility.php?"

    //SUPPORT API
    //TODO : Need to amend 'subject' in api
    const val CREATE_TICKET_API = "information/support_mailer.php?"

    //ABOUT US
    const val ATRIA_TND_URL = "http://www.atria.com.my/atria/terms-of-use.html"
    const val ATRAI_PDPA_URL = "http://www.atria.com.my/atria/personal-data-protection-act.html"

    //PROFILE API
    const val PROFILE_API = "profile.php?"
    const val UPDATE_PROFILE_API = "updateProfile.php?"
    const val UPDATE_PROFILE_PIC_API = "uploadProfilePic.php?"
    const val RESEND_ACTIVATION_LINK = "/resend_activation_link.php?"
    const val CHANGE_PASSWORD_API = "changepass.php?"
    const val SET_PASSWORD_API = "setPass.php?"
    const val MIGRATION_RESET_PASSWORD_API = "migration_resetpass_email_api.php?"
    const val FORGET_PASSWORD_API = "e-commerce/api/forgetPass2.php?"
    const val URL_SMS_RESET_OTP = "e-commerce/api/sms_reset_otp_verification.php?"
    const val URL_UPDATE_PASSWORD_FROM_SMS = "api/changepass.php?"

    //SHIPPING ADDRESS API
    const val CREATE_SHIPPING_ADDRESS_API = "/setShippingAddress.php?"
    const val EDIT_SHIPPING_ADDRESS_API = "/updateShippingAddress.php?"
    const val GET_SHIPPING_ADDRESS_API = "/getShippingAddress.php?"
    const val DELETE_SHIPPING_ADDRESS_API = "/deleteShippingAddress.php?"

    //NOTIFICATION API
    const val GET_NOTI_API = "getnoti.php?"
    const val SAVE_NOTI_API = "savenoti.php?"
    const val INBOX_ACTION_API = "inbox_action.php?"
    const val INBOX_LISTING_API = "inbox_listing.php?"

    //NEARBY REWARD API
    const val FEED_VERSION_API = "feed_version.php"
    const val GEOFENCE_API = "getResult2.php"
    const val GEOFENCE_DETAIL_API = "geofence_details.php"
    const val BEACON_API = "getResult.php"
    const val BEACON_DETAIL_API = "push_details.php"
    const val ACTION_BUTTON_ANALYTIC_API = "action_button_respond.php?"
    const val VERSION_DIALOG_CHECKING_API = "alertList.php?"
    const val IN_APP_ALERT_API = "/member_alertList.php?"
    const val REFRESH_TOKEN = "/refreshToken.php?"

    //SCAN RECEIPT API
    const val UPLOAD_IMAGE_API = "uploadReceipt.php?"
    const val SCAN_RECEIPT_API = "addReceipt.php?"
    const val UPLOAD_RECEIPT_DIRECT_API = "uploadReceiptDirect.php?"



    //JEWEL BOX API
    const val JEWEL_CATEGORY_API = "/purchase_categories.php?"
    const val JEWEL_BOX_API = "/getJewelBox.php?"
    const val PURCHASE_RATING_API = "/purchaseRating.php?"
    const val TRANSFER_OWNERSHIP_API = "/transferPurchase.php?"

    const val BRANCH_STATES_API = "branch_states.php?"
    const val BRANCH_AREAS_API = "/branch_city.php?"
    const val BRANCH_LOCATIONS_API = "/getBranches.php?"

    const val ADD_POINTS_API = "giveRewardPoints.php?"
    const val COUNTRY_LIST_API = "countryList.php?"
    const val HONORIFIC_LIST_API = "e-commerce/api/honorificList.php?"
    const val INCOME_INTEREST_API = "/registration_selection.php?"
    const val NOTIFICATION_HISTORY_API = "/getNotifications.php?"
    const val MALL_LIST_API = "malllist.php?"
    const val POINT_DETAIL_API = "PointDetails.php?"
    const val USEFUL_LINKS_API = "usefullinks.php?"
    const val MAKE_TOURIST_API = "/makeCustomerTourist.php?"
    const val REFERRAL_DATA_API = "getReferralRewardData.php?"
    const val CHECK_REFERRAL_API = "check_valid_referral.php?"
    const val RELEASE_POINTS_API = "releasePoints.php?"

    const val DAILY_REWARD_API = "get_daily_check_in_rewards.php?"
    const val NEARBY_REWARDS = "nearby_rewards?"
    const val STAMPS_REWARDS_API = "get_stamp_rewards.php?"
    const val STAMPS_REWARDS_CHECKIN_API = "stamp_rewards.php?"
    const val CHECK_IN_API = "daily_check_in_rewards.php?"
    const val UPDATE_PAYABLE_BY_POINTS = "updatePayableByPoints.php?"
    const val SURVEY_API = "/view_survey_uncompleted.php?"
    const val COUNTRY_CODES_API = "getCountryCodes.php"

//test
}