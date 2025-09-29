package com.fireworks.myeventsdk.NetworkService

import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.model.ApiResponse
import com.fireworks.myeventsdk.model.CheckOutResponse
import com.fireworks.myeventsdk.model.CommonResponse
import com.fireworks.myeventsdk.model.EventCategoryResponse
import com.fireworks.myeventsdk.model.EventResponse
import com.fireworks.myeventsdk.model.GetSettingsResponse
import com.fireworks.myeventsdk.model.Profile.ProfileResponse
import com.fireworks.myeventsdk.model.events_detail.EventsDetailResponse
import com.fireworks.myeventsdk.model.events_detail.EventsResponse
import com.fireworks.myeventsdk.model.login.LoginResponse
import com.fireworks.myeventsdk.model.HonorificListResponse
import com.fireworks.myeventsdk.model.MerchantCategory
import com.fireworks.myeventsdk.model.PRIVILEGEdASH.MainResponse
import com.fireworks.myeventsdk.model.PointDetailResponse
import com.fireworks.myeventsdk.model.SubResponse
import com.fireworks.myeventsdk.model.TimerResponse
import com.fireworks.myeventsdk.model.UploadImageResponse
import com.fireworks.myeventsdk.model.VerifyOtpResponse
import com.fireworks.myeventsdk.model.VerifyPhoneNumberResponse
import com.fireworks.myeventsdk.model.directory.DirectoryResponse
import com.fireworks.myeventsdk.model.directory.category.DirectoryCategoryResponse
import com.fireworks.myeventsdk.model.directory.detail.DirectoryDetailResponse
import com.fireworks.myeventsdk.model.directory.floor.DirectoryFloorResponse
import com.incredibleqr.mysogo.data.remote.model.ShippingPointResponse
import com.incredibleqr.mysogo.data.remote.model.branch.states.StatesResponse
import com.incredibleqr.mysogo.data.remote.model.dailyReward.DailyRewardResponse
import com.incredibleqr.mysogo.data.remote.model.daily_check_in.DailyCheckInResponse
import com.fireworks.myeventsdk.model.dashboard.DashboardResponse
import com.fireworks.myeventsdk.model.dialogs.VersionChecking
import com.fireworks.myeventsdk.model.facility.FacilityResponse
import com.fireworks.myeventsdk.model.facility.category.FacilityCategoryResponse
import com.fireworks.myeventsdk.model.facility.floor.FacilityFloorResponse
import com.fireworks.myeventsdk.model.links.UsefulLinksResponse
import com.fireworks.myeventsdk.model.news.NewsResponse
import com.fireworks.myeventsdk.model.news.detail.NewsDetailResponse
import com.incredibleqr.mysogo.data.remote.model.ReferralDataResponse
import com.incredibleqr.mysogo.data.remote.model.inAppAlert.InAppAlertResponse
import com.incredibleqr.mysogo.data.remote.model.mall.MultiMallResponse
import com.fireworks.myeventsdk.model.notifications.NotificationResponse
import com.incredibleqr.mysogo.data.remote.model.reward.RewardResponse
import com.fireworks.myeventsdk.model.reward.category.RewardCategoryResponse
import com.fireworks.myeventsdk.model.reward.detail.RewardDetailResponse
import com.fireworks.myeventsdk.model.shop.ShopResponse
import com.fireworks.myeventsdk.model.support.SupportResponse
import com.fireworks.myeventsdk.model.transaction.TransactionDetailResponse
import com.fireworks.myeventsdk.model.transaction.TransactionResponse
import com.fireworks.myeventsdk.model.wallet.CheckMigrateUserResponse
import com.fireworks.myeventsdk.model.wallet.WalletResponse
import com.fireworks.myeventsdk.model.migratedUser.MigratedUserDataResponse
import com.fireworks.myeventsdk.model.register.RegisterResponse
import com.fireworks.myeventsdk.model.country.CountryListResponse
import com.fireworks.myeventsdk.model.reward.category.FilterData
import com.fireworks.myeventsdk.model.reward.category.RewardCategory
import com.fireworks.myeventsdk.model.wallet.detail.WalletDetailResponse
import com.fireworks.myeventsdk.model.wallet.multiple.WalletMultipleResponse
import com.incredibleqr.mysogo.data.remote.model.MerchantRewardResponse
import com.incredibleqr.mysogo.data.remote.model.country_codes.CountryCodesResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.QueryMap
//test
interface Service {

    @FormUrlEncoded
    @POST(Constants.EVENT_API)
    suspend fun getEvents(@Header("X-Auth-Token") sectoken: String,
    @FieldMap fields: Map<String, String>): Response<EventResponse>



    @FormUrlEncoded
    @POST(Constants.FAVOURITE_API)
    suspend fun getFavouriteApi(@FieldMap fields: Map<String, String>): Response<SubResponse>



    @FormUrlEncoded
    @POST(Constants.EVENT_DETAIL_API)
    suspend fun getEventsDetails(@Header("X-Auth-Token") secToken: String,
    @FieldMap fields: Map<String, String>): Response<EventsDetailResponse>


    @FormUrlEncoded
    @POST(Constants.EVENT_CHECKOUT_API)
    suspend fun getEventCheckout(@Header("X-Auth-Token") secToken: String,
    @FieldMap fiellds: Map<String, String>): Response<CheckOutResponse>


    @FormUrlEncoded
    @POST(Constants.EVENT_CATEGORIES_API)
    suspend fun getEventCategory(@FieldMap fiellds: Map<String, String>): Response<EventCategoryResponse>



    @FormUrlEncoded
    @POST(Constants.LOGIN_API)
    suspend fun loginAPI(@FieldMap fields: Map<String, String>): Response<LoginResponse>


   @FormUrlEncoded
    @POST(Constants.loginV2_with_otp)
    suspend fun loginWithOtp(@FieldMap fields: Map<String, String>): Response<LoginResponse>


    @FormUrlEncoded
    @POST(Constants.CHECK_MOBILENUMBER)
    suspend fun validateMobileNumber(@FieldMap fields: Map<String, String>): Response<VerifyPhoneNumberResponse>


    @FormUrlEncoded
    @POST(Constants.LOGIN_OTP_API)
    suspend fun loginOtpAPI(@FieldMap fields: Map<String, String>): Response<CommonResponse>



   @FormUrlEncoded
    @POST(Constants.DELETE_USER)
    suspend fun deleteUser(@FieldMap fields: Map<String, String>): Response<CommonResponse>


    @FormUrlEncoded
    @POST(Constants.SEND_OTP_API)
    suspend fun getOTP(@FieldMap fields: Map<String, String>): Response<CommonResponse>

       @FormUrlEncoded
    @POST(Constants.OTP_VERIFY_API)
    suspend fun getOtpVerify(@FieldMap fields: Map<String, String>): Response<CommonResponse>




    @FormUrlEncoded
    @POST(Constants.PROFILE_API)
    suspend fun profileAPI(@FieldMap fields: Map<String, String>): Response<ProfileResponse>


    @FormUrlEncoded
    @POST(Constants.UPDATE_PROFILE_API)
    suspend fun updateProfileAPI(@FieldMap fields: Map<String, String>): Response<CommonResponse>

    @FormUrlEncoded
    @POST(Constants.ADD_POINTS_API)
    suspend fun givePoints(@FieldMap fields: Map<String, String>): Response<CommonResponse>


    @POST(Constants.PURCHASE_RATING_API)
    @FormUrlEncoded
    suspend fun ratePurchase(@FieldMap params: Map<String, String>): Response<CommonResponse>


    @POST(Constants.DASHBOARD_API)
    @FormUrlEncoded
    suspend fun dashboardAPI(@FieldMap params: Map<String, String>): Response<DashboardResponse>

    @POST(Constants.PRIVILEGE_DASH)
    @FormUrlEncoded
    suspend fun privDashAPI(@FieldMap params: Map<String, String>): Response<MainResponse>


    @POST(Constants.MALL_LIST_API)
    @FormUrlEncoded
    suspend fun getMallList(@FieldMap params: Map<String, String>): Response<MultiMallResponse>


    @POST(Constants.ADD_DEVICE_TOKEN_API)
    @FormUrlEncoded
    suspend fun addDeviceTokenAPI(@FieldMap params: Map<String, String>): Response<CommonResponse>


    @POST(Constants.IN_APP_ALERT_API)
    @FormUrlEncoded
    suspend fun inAppAlertsAPI(@FieldMap fields: Map<String, String>): Response<InAppAlertResponse>


    @GET(Constants.UPDATE_PAYABLE_BY_POINTS)
    suspend fun updatePayableByPoints(@QueryMap queryMap: Map<String, String>): Response<CommonResponse>

    @FormUrlEncoded
    @POST(Constants.VERSION_DIALOG_CHECKING_API)
    suspend fun versioningAPI(@FieldMap params: Map<String, String>): Response<VersionChecking>


    @FormUrlEncoded
    @POST(Constants.STAMPS_REWARDS_API)
    suspend fun getStampRewards(@FieldMap params: Map<String, String>): Response<DailyRewardResponse>


    @FormUrlEncoded
    @POST(Constants.STAMPS_REWARDS_CHECKIN_API)
    suspend fun checkInStampReward(@FieldMap params: Map<String, String>): Response<DailyCheckInResponse>

    @FormUrlEncoded
    @POST(Constants.DAILY_REWARD_API)
    suspend fun getDailyRewards(@FieldMap params: Map<String, String>): Response<DailyRewardResponse>


    @FormUrlEncoded
    @POST(Constants.CHECK_IN_API)
    suspend fun checkInReward(@FieldMap params: Map<String, String>): Response<DailyCheckInResponse>


    @FormUrlEncoded
    @POST(Constants.WALLET_DETAIL_API)
    suspend fun walletDetailAPI(@FieldMap params: Map<String, String>): Response<WalletDetailResponse>


    @FormUrlEncoded
    @POST(Constants.WALLET_API)
    suspend fun walletAPI(@FieldMap params: Map<String, String>): Response<WalletResponse>


    @FormUrlEncoded
    @POST(Constants.VERIFY_PASSWORD_API)
    suspend fun verifyPasswordAPI(@FieldMap params: Map<String, String>): Response<CommonResponse>



    @FormUrlEncoded
    @POST(Constants.GIFT_CHECKOUT_API)
    suspend fun pickupCheckoutAPI(@FieldMap fields: Map<String, String>): Response<CheckOutResponse>


    @FormUrlEncoded
    @POST(Constants.REWARD_CHECKOUT_API)
    suspend fun rewardCheckoutAPI(@FieldMap fields: Map<String, String>): Response<CheckOutResponse>

    @FormUrlEncoded
    @POST(Constants.TIMER_REWARD)
    suspend fun timerRewardAPI(@FieldMap fields: Map<String, String>): Response<TimerResponse>


    @FormUrlEncoded
    @POST(Constants.SHIPPING_POINTS_API)
    suspend fun getShippingPointsByName(@FieldMap fields: Map<String, String>): Response<ShippingPointResponse>


    @FormUrlEncoded
    @POST(Constants.DIRECTORY_LIST_REWARD)
    suspend fun getDirectoryListInReward(@FieldMap fields: Map<String, String>): Response<MerchantRewardResponse>


    @FormUrlEncoded
    @POST(Constants.BRANCH_STATES_API)
    suspend fun getStates(@FieldMap fields: Map<String, String>): Response<StatesResponse>

    @FormUrlEncoded
    @POST(Constants.REWARD_DETAIL_API)
    suspend fun rewardDetailAPI(@FieldMap fields: Map<String, String>): Response<RewardDetailResponse>


    @FormUrlEncoded
    @POST(Constants.REWARD_API)
    suspend fun rewardAPI(@FieldMap fields: Map<String, String>): Response<RewardResponse>

    @FormUrlEncoded
    @POST(Constants.NEW_REWARDS)
    suspend fun rewardnNewAPI(@FieldMap fields: Map<String, String>): Response<RewardResponse>

    @FormUrlEncoded
    @POST(Constants.PURCHASED_COUNT)
    suspend fun purchaseCountApi(@FieldMap fields: Map<String, String>): Response<ApiResponse>


    @FormUrlEncoded
    @POST(Constants.REWARD_CATEGORIES)
    suspend fun rewardCategoriesAPI(@FieldMap fields: Map<String, String>): Response<FilterData>

    @FormUrlEncoded
    @POST(Constants.NEARBY_REWARDS)
    suspend fun nearByrewardAPI(@FieldMap fields: Map<String, String>): Response<RewardResponse>

    @FormUrlEncoded
    @POST(Constants.REWARD_CATEGORY_API)
    suspend fun rewardCategoryAPI(@FieldMap fields: Map<String, String>): Response<RewardCategoryResponse>


    @FormUrlEncoded
    @POST(Constants.DIRECTORY_DETAIL_API)
    suspend fun directoryDetailAPI(@FieldMap fields: Map<String, String>): Response<DirectoryDetailResponse>


    @FormUrlEncoded
    @POST(Constants.DIRECTORY_API)
    suspend fun directoryAPI(@FieldMap fields: Map<String, String>): Response<DirectoryResponse>


    @FormUrlEncoded
    @POST(Constants.DIRECTORY_API)
    suspend fun directorySearchAPI(@FieldMap fields: Map<String, String>): Response<DirectoryResponse>


    @FormUrlEncoded
    @POST(Constants.DIRECTORY_CATEGORY_API)
    suspend fun directoryCategoryAPI(@FieldMap fields: Map<String, String>): Response<DirectoryCategoryResponse>


    @FormUrlEncoded
    @POST(Constants.DIRECTORY_FLOOR_API)
    suspend fun directoryFloorAPI(@FieldMap fields: Map<String, String>): Response<DirectoryFloorResponse>


    @POST(Constants.FACILITY_API)
    @FormUrlEncoded
    suspend fun facilityAPI(@FieldMap fields: Map<String, String>): Response<FacilityResponse>


    @POST(Constants.FACILITY_API)
    @FormUrlEncoded
    suspend fun facilitySearchAPI(@FieldMap fields: Map<String, String>): Response<FacilityResponse>


    @POST(Constants.FACILITY_CATEGORY_API)
    @FormUrlEncoded
    suspend fun facilityCategoryAPI(@FieldMap fields: Map<String, String>): Response<FacilityCategoryResponse>


    @FormUrlEncoded
    @POST(Constants.FACILITY_FLOOR_API)
    suspend fun facilityFloorAPI(@FieldMap fields: Map<String, String>): Response<FacilityFloorResponse>


    @FormUrlEncoded
    @POST(Constants.REFERRAL_DATA_API)
    suspend fun getReferData(@FieldMap fields: Map<String, String>): Response<ReferralDataResponse>
   @FormUrlEncoded
    @POST(Constants.CHECK_REFERRAL_API)
    suspend fun getReferAPI(@FieldMap fields: Map<String, String>): Response<ReferralDataResponse>

    @FormUrlEncoded
    @POST(Constants.USEFUL_LINKS_API)
    suspend fun getUsefulLinks(@FieldMap fields: Map<String, String>): Response<UsefulLinksResponse>


    @FormUrlEncoded
    @POST(Constants.NEWS_DETAIL_API)
    suspend fun newsDetailAPI(@FieldMap fields: Map<String, String>): Response<NewsDetailResponse>


    @FormUrlEncoded
    @POST(Constants.NEWS_API)
    suspend fun newsAPI(@FieldMap fields: Map<String, String>): Response<NewsResponse>


    @FormUrlEncoded
    @POST(Constants.INBOX_LISTING_API)
    suspend fun getInbox(@FieldMap params: Map<String, String>): Response<NotificationResponse>

    @FormUrlEncoded
    @POST(Constants.INBOX_ACTION_API)
    suspend fun inboxAction(@FieldMap fields: Map<String, String>): Response<CommonResponse>


    @FormUrlEncoded
    @POST(Constants.CHANGE_PASSWORD_API)
    suspend fun changePasswordAPI(@FieldMap fields: Map<String, String>): Response<CommonResponse>


    @FormUrlEncoded
    @POST(Constants.SET_PASSWORD_API)
    suspend fun setPasswordAPI(@FieldMap fields: Map<String, String>): Response<CommonResponse>


    @FormUrlEncoded
    @POST(Constants.FORGET_PASSWORD_API)
    suspend fun fogotPasswordAPI(@FieldMap fields: Map<String, String>): Response<CommonResponse>


     @FormUrlEncoded
     @POST(Constants.URL_SMS_RESET_OTP)
     suspend fun resetOtp(@FieldMap fields: Map<String, String>): Response<VerifyOtpResponse>



 @FormUrlEncoded
    @POST(Constants.UPLOAD_IMAGE_API)
    suspend fun uploadImageAPI(@FieldMap fields: Map<String, String>): Response<UploadImageResponse>

    @FormUrlEncoded
    @POST(Constants.SCAN_RECEIPT_API)
    suspend fun scanReceiptAPI(@FieldMap fields: Map<String, String>): Response<CommonResponse>


    @FormUrlEncoded
    @POST(Constants.UPLOAD_RECEIPT_DIRECT_API)
    suspend fun uploadScanReceiptAPI(@FieldMap fields: Map<String, String>): Response<UploadImageResponse>


    @FormUrlEncoded
    @POST(Constants.MERCHANT_BRANCH_API)
    suspend fun shopAPI(@FieldMap fields: Map<String, String>): Response<ShopResponse>


    @FormUrlEncoded
    @POST(Constants.MERCHANT_CATEGORIES)
    suspend fun merchantCategory(@FieldMap fields: Map<String, String>): Response<MerchantCategory>


    @FormUrlEncoded
    @POST(Constants.GET_SETTINGS_API)
    suspend fun getSettingsAPI(@FieldMap fields: Map<String, String>): Response<GetSettingsResponse>

    @FormUrlEncoded
    @POST(Constants.UPDATE_SETTINGS_API)
    suspend fun updateSettingsAPI(@FieldMap fields: Map<String, String>): Response<CommonResponse>


    @FormUrlEncoded
    @POST(Constants.CREATE_TICKET_API)
    suspend fun supportAPI(@FieldMap fields: Map<String, String>): Response<SupportResponse>


    @FormUrlEncoded
    @POST(Constants.TRANSACTION_HISTORY_API)
    suspend fun transactionAPI(@FieldMap fields: Map<String, String>): Response<TransactionResponse>


    @FormUrlEncoded
    @POST(Constants.TRANSACTION_HISTORY_DETAIL_API)
    suspend fun transactionDetailAPI(@FieldMap fields: Map<String, String>): Response<TransactionDetailResponse>

    @POST(Constants.RELEASE_POINTS_API)
    @FormUrlEncoded
    suspend fun releasePoints(@FieldMap fields: Map<String, String>): Response<CommonResponse>

    @FormUrlEncoded
    @POST(Constants.ARCHIVE_TRANSACTION_API)
    suspend fun archiveTransaction(@FieldMap fields: Map<String, String>): Response<CommonResponse>


    @POST(Constants.WALLET_MULTIPLE_API)
    @FormUrlEncoded
    suspend fun walletMultipleAPI(@FieldMap fields: Map<String, String>): Response<WalletMultipleResponse>

    @FormUrlEncoded
    @POST(Constants.CHECK_MIGRATED_USER_API)
    suspend fun checkMigratedUser(@FieldMap fields: Map<String, String>): Response<CheckMigrateUserResponse>

    @FormUrlEncoded
    @POST(Constants.GET_MIGRATED_DATA_API)
    suspend fun getMigratedUserData(@FieldMap fields: Map<String, String>): Response<MigratedUserDataResponse>


    @FormUrlEncoded
    @POST(Constants.SIGN_UP_API)
    suspend fun signUpAPI(@FieldMap fields: Map<String, String>): Response<RegisterResponse>


    @FormUrlEncoded
    @POST(Constants.REGISTER_API)
    suspend fun registerAPI(@FieldMap fields: Map<String, String>): Response<RegisterResponse>


    @FormUrlEncoded
    @POST(Constants.HONORIFIC_LIST_API)
    suspend fun getHonorificList(@FieldMap fields: Map<String, String>): Response<HonorificListResponse>


    @FormUrlEncoded
    @POST(Constants.COUNTRY_LIST_API)
    suspend fun getCountryList(@FieldMap fields: Map<String, String>): Response<CountryListResponse>

    @GET(Constants.COUNTRY_CODES_API)
    suspend fun getCountryCodes(): Response<CountryCodesResponse>

    @POST(Constants.POINT_DETAIL_API)
    suspend fun getPointDetails(): Response<PointDetailResponse>


}