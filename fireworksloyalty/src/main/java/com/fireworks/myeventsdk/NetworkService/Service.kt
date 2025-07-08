package com.fireworks.myeventsdk.NetworkService

import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.model.CheckOutResponse
import com.fireworks.myeventsdk.model.CommonResponse
import com.fireworks.myeventsdk.model.GetSettingsResponse
import com.fireworks.myeventsdk.model.Profile.ProfileResponse
import com.fireworks.myeventsdk.model.events_detail.EventsDetailResponse
import com.fireworks.myeventsdk.model.events_detail.EventsResponse
import com.fireworks.myeventsdk.model.login.LoginResponse
import com.fireworks.myeventsdk.model.promotions_news.PromotionNewsResponse
import com.fireworks.myeventsdk.model.HighlightsCategoriesResponse
import com.fireworks.myeventsdk.model.HonorificListResponse
import com.fireworks.myeventsdk.model.UploadImageResponse
import com.fireworks.myeventsdk.model.directory.DirectoryResponse
import com.fireworks.myeventsdk.model.directory.category.DirectoryCategoryResponse
import com.fireworks.myeventsdk.model.directory.detail.DirectoryDetailResponse
import com.fireworks.myeventsdk.model.directory.floor.DirectoryFloorResponse
import com.incredibleqr.mysogo.data.remote.model.ShippingPointResponse
import com.incredibleqr.mysogo.data.remote.model.branch.states.StatesResponse
import com.incredibleqr.mysogo.data.remote.model.dailyReward.DailyRewardResponse
import com.incredibleqr.mysogo.data.remote.model.daily_check_in.DailyCheckInResponse
import com.incredibleqr.mysogo.data.remote.model.dashboard.DashboardResponse
import com.incredibleqr.mysogo.data.remote.model.dialogs.VersionChecking
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
import com.incredibleqr.mysogo.data.remote.model.reward.category.RewardCategoryResponse
import com.incredibleqr.mysogo.data.remote.model.reward.detail.RewardDetailResponse
import com.fireworks.myeventsdk.model.shop.ShopResponse
import com.fireworks.myeventsdk.model.support.SupportResponse
import com.fireworks.myeventsdk.model.transaction.TransactionDetailResponse
import com.fireworks.myeventsdk.model.transaction.TransactionResponse
import com.fireworks.myeventsdk.model.wallet.CheckMigrateUserResponse
import com.fireworks.myeventsdk.model.wallet.WalletResponse
import com.fireworks.myeventsdk.model.migratedUser.MigratedUserDataResponse
import com.fireworks.myeventsdk.model.register.RegisterResponse
import com.fireworks.myeventsdk.model.country.CountryListResponse
import com.incredibleqr.mysogo.data.remote.model.wallet.detail.WalletDetailResponse
import com.incredibleqr.mysogo.data.remote.model.wallet.multiple.WalletMultipleResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface Service {

    @FormUrlEncoded
    @POST(Constants.EVENT_API)
    suspend fun getEvents(
        @Header("X-Auth-Token") sectoken: String,
        @Field("cat_filter") category: String?,
        @Field("search_item") searchItem: String?,
        @Field("mall") mall: String,
        @Field("latest") isLatest: String,
        @Field("custcode") custID: String,
        @Field("date") date: String,
        @Field("vc") vc: String
    ): Response<EventsResponse>


//    @GET(Constants.EVENTS_CATEGORY_ITEM)
    suspend fun getEventsCategories(
    @Header("X-Auth-Token") secToken: String,
    @Query("custcode") customerId: String?
    ): Response<HighlightsCategoriesResponse>


    @POST(Constants.EVENT_DETAIL_API)
    @FormUrlEncoded
    suspend fun getEventsDetails(
        @Header("X-Auth-Token") secToken: String,
        @Field("eventid") event_id: String,
        @Field("date") date: String = NetworkUtils.unixTimeStamp().toString(),
        @Field("custcode") customer_id: String?,
        @Field("mall") mall: Int = 12,
        @Field("vc") vc: String = NetworkUtils.getVCKey()
    ): Response<EventsDetailResponse>

//    @POST(Constants.URL_PROMOTIONS_NEWS)
    @FormUrlEncoded
    suspend fun getNews(
    @Header("X-Auth-Token") secToken: String,
    @Field("category") category: String = "promotion",
    @Field("mall") mall: String = "12",
    @Field("search_item") search: String = "",
    @Field("cat_filter") filter: String? = "",
    @Field("latest") isLatest: String = "1",
    @Field("date") date: String = NetworkUtils.unixTimeStamp().toString(),
    @Field("vc") vc: String = NetworkUtils.getVCKey()
    ): Response<PromotionNewsResponse>

    @FormUrlEncoded
    @POST(Constants.LOGIN_API)
    suspend fun loginAPI(
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("phone_country") phoneCountry: String,
        @Field("nric") nric: String,
        @Field("password") password: String,
        @Field("socialmediatype") socialType: String,
        @Field("socialmediatoken") socialToken: String,
        @Field("mercid") merchantId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceId: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") svc: String,
        @Field("pvc") pvc: String
    ): Response<LoginResponse>


    @POST(Constants.SEND_OTP_API)
    @FormUrlEncoded
    fun getOTP(
        @Field("phone") phone: String,
        @Field("phone_country") phoneCountry: String,
        @Field("vc") vc: String,
        @Field("date") date: String,
        @Field("lang") lang: String,
        @Field("os") os: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype",) deviceType: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.PROFILE_API)
    @FormUrlEncoded
    fun profileAPI(
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String,
        @Field("pvc") pv:String
    ): Response<ProfileResponse>



    @POST(Constants.UPDATE_PROFILE_API)
    @FormUrlEncoded
    fun updateProfileAPI(
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("title") title: String,
        @Field("fname") firstName: String,
        @Field("lname") lastName: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("phone_country") countryCode: String,
        @Field("dob") dob: String,
        @Field("nric") nric: String,
        @Field("id_type") idType: Int,
        @Field("display_name") prefName: String,
        @Field("gender") gender: String,
        @Field("love_anniversary") anniv: String,
        @Field("nationality") nationality: String,
        @Field("country") country: String,
        @Field("race") race: String,
        @Field("householdincome") income: Int,
        @Field("selectedinterests") interests: String,
        @Field("address1") addressone: String,
        @Field("address2") addresstwo: String,
        @Field("state") state: String,
        @Field("city") city: String,
        @Field("postcode") postcode: String,
        @Field("mall") preferredMall: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String,
        @Field("pvc") pv:String
    ): Response<CommonResponse>


    @POST(Constants.ADD_POINTS_API)
    @FormUrlEncoded
    fun givePoints(
        @Field("reward_type") rewardType: String,
        @Field("custid") custId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.PURCHASE_RATING_API)
    @FormUrlEncoded
    fun ratePurchase(
        @Field("custid") custid: String,
        @Field("purchaseid") purchaseid: String,
        @Field("rating") rating: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.DASHBOARD_API)
    @FormUrlEncoded
    fun dashboardAPI(
        @Field("mall") mall: String,
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("lat") latitude: Double,
        @Field("lng") longitude: Double,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("appversion") appVersion: String,
        @Field("deviceflavour") deviceflavour: String,
        @Field("svc") sv: String,
        @Field("pvc") pv:String
    ): Response<DashboardResponse>

    @POST(Constants.MALL_LIST_API)
    @FormUrlEncoded
    fun getMallList(
        @Field("date") date: String,
        @Field("vc") vc: String, @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<MultiMallResponse>


    @POST(Constants.ADD_DEVICE_TOKEN_API)
    @FormUrlEncoded
    fun addDeviceTokenAPI(
        @Field("token") token: String,
        @Field("custid") custId: String,
        @Field("deviceid") deviceId: String,
        @Field("devicetype") deviceType: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.IN_APP_ALERT_API)
    @FormUrlEncoded
    fun inAppAlertsAPI(
        @Field("vc") vc: String,
        @Field("date") date: String,
        @Field("custid") custId: String,
        @Field("svc") sv: String
    ): Response<InAppAlertResponse>


    @GET(Constants.UPDATE_PAYABLE_BY_POINTS)
    suspend fun updatePayableByPoints(
        @Query("vc") vc: String,
        @Query("date") date: String,
        @Query("custid") customerID: String,
        @Query("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.VERSION_DIALOG_CHECKING_API) // version check
    @FormUrlEncoded
    fun versioningAPI(
        @Field("vc") vc: String,
        @Field("date") date: String,
        @Field("custid") custId: String,
        @Field("lang") lang: String,
        @Field("os") os: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("appversion") appVersion: String,
        @Field("deviceflavour") deviceflavour: String,
        @Field("svc") sv: String
    ): Response<VersionChecking>


    @POST(Constants.DAILY_REWARD_API)
    @FormUrlEncoded
    fun getDailyRewards(
        @Field("custid") custId: String, @Field("date") date: String,
        @Field("vc") vc: String, @Field("os") os: String,
        @Field("phonename") phoneName: String, @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<DailyRewardResponse>


    @POST(Constants.CHECK_IN_API)
    @FormUrlEncoded
    fun checkInReward(
        @Field("custid") custId: String, @Field("date") date: String,
        @Field("vc") vc: String, @Field("os") os: String,
        @Field("phonename") phoneName: String, @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<DailyCheckInResponse>


    @POST(Constants.WALLET_DETAIL_API)
    @FormUrlEncoded
    fun walletDetailAPI(
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("type") walletType: String,
        @Field("id") id: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<WalletDetailResponse>


    @POST(Constants.WALLET_API)
    @FormUrlEncoded
    fun walletAPI(
        @Field("mall") mall: String,
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("type") walletType: String,
        @Field("condition") codition: String,
        @Field("start") start: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<WalletResponse>



    @POST(Constants.VERIFY_PASSWORD_API)
    @FormUrlEncoded
    fun verifyPasswordAPI(
        @Field("custid") custId: String,
        @Field("pass") password: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.GIFT_CHECKOUT_API)
    @FormUrlEncoded
    fun pickupCheckoutAPI(
        @Field("itemid") itemId: String,
        @Field("custid") custId: String,
        @Field("qty") quantity: String,
        @Field("collection_method") method: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<CheckOutResponse>


    @POST(Constants.REWARD_CHECKOUT_API)
    @FormUrlEncoded
    fun rewardCheckoutAPI(
        @Field("itemid") itemId: String,
        @Field("custid") custId: String,
        @Field("qty") quantity: String,
        @Field("collection_method") method: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String,
        @Field("pvc") pv:String
    ): Response<CheckOutResponse>

    @POST(Constants.SHIPPING_POINTS_API)
    @FormUrlEncoded
    fun getShippingPointsByName(
        @Field("reward_id") rewardId: String,
        @Field("statename") stateName: String, @Field("date") date: String,
        @Field("vc") vc: String, @Field("os") os: String,
        @Field("phonename") phoneName: String, @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<ShippingPointResponse>


    @POST(Constants.BRANCH_STATES_API)
    @FormUrlEncoded
    fun getStates(
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field(
            "phonename"
        ) phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<StatesResponse>

    @POST(Constants.REWARD_DETAIL_API)
    @FormUrlEncoded
    fun rewardDetailAPI(
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("id") couponId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String ,
        @Field("pvc") pv:String
    ): Response<RewardDetailResponse>


    @POST(Constants.REWARD_API)
    @FormUrlEncoded
    fun rewardAPI(
        @Field("mall") mall: String,
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("offset") offset: String,
        @Field("location") location: String,
        @Field("latitude") latitude: String,
        @Field("longitude") longitude: String,
        @Field("category") categoryId: String,
        @Field("start") start: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<RewardResponse>


    @POST(Constants.REWARD_CATEGORY_API)
    @FormUrlEncoded
    fun rewardCategoryAPI(
        @Field("mercid") merchantId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field(
            "os"
        ) os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field(
            "sectoken"
        ) sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field(
            "devicetype"
        ) deviceType: String,
        @Field("svc") sv: String
    ): Response<RewardCategoryResponse>



    @POST(Constants.DIRECTORY_DETAIL_API)
    @FormUrlEncoded
    fun directoryDetailAPI(
        @Field("custid") custid: String,
        @Field("mall") mall: Int,
        @Field("merchantid") merchantId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String, @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<DirectoryDetailResponse>



    @POST(Constants.DIRECTORY_API)
    @FormUrlEncoded
    fun directoryAPI(
        @Field("categoryid") categoryId: String,
        @Field("mall") mall: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<DirectoryResponse>


    @POST(Constants.DIRECTORY_API)
    @FormUrlEncoded
    fun directorySearchAPI(
        @Field("mall") mall: Int,
        @Field("searchterm") searchTerm: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<DirectoryResponse>


    @POST(Constants.DIRECTORY_CATEGORY_API)
    @FormUrlEncoded
    fun directoryCategoryAPI(
        @Field("merchantid") merchantId: String,
        @Field("mall") mall: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<DirectoryCategoryResponse>


    @POST(Constants.DIRECTORY_FLOOR_API)
    @FormUrlEncoded
    fun directoryFloorAPI(
        @Field("mall") mall: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field(
            "phonename"
        ) phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<DirectoryFloorResponse>

    @POST(Constants.FACILITY_API)
    @FormUrlEncoded
    fun facilityAPI(
        @Field("categoryid") categoryId: String,
        @Field("mall") mall: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<FacilityResponse>

    @POST(Constants.FACILITY_API)
    @FormUrlEncoded
    fun facilitySearchAPI(
        @Field("mall") mall: Int,
        @Field("searchterm") searchTerm: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<FacilityResponse>


    @POST(Constants.FACILITY_CATEGORY_API)
    @FormUrlEncoded
    fun facilityCategoryAPI(
        @Field("merchantid") merchantId: String,
        @Field("mall") mall: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<FacilityCategoryResponse>


    @POST(Constants.FACILITY_FLOOR_API)
    @FormUrlEncoded
    fun facilityFloorAPI(
        @Field("mall") mall: Int,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<FacilityFloorResponse>


    @POST(Constants.REFERRAL_DATA_API)
    @FormUrlEncoded
    fun getReferData(
        @Field("vc") vc: String,
        @Field("date") date: String,
        @Field("custid") custId: String,
        @Field("svc") sv: String
    ): Response<ReferralDataResponse>


    @POST(Constants.USEFUL_LINKS_API)
    @FormUrlEncoded
    fun getUsefulLinks(
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<UsefulLinksResponse>


    @POST(Constants.NEWS_DETAIL_API)
    @FormUrlEncoded
    fun newsDetailAPI(
        @Field("mall") mall: String,
        @Field("newsid") newsId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<NewsDetailResponse>



    @POST(Constants.NEWS_API)
    @FormUrlEncoded
    fun newsAPI(
        @Field("mall") mall: String,
        @Field("latest") latest: String,
        @Field("start") start: Int,
        @Field("category") category: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<NewsResponse>


    @POST(Constants.INBOX_LISTING_API)
    @FormUrlEncoded
    fun getInbox(
        @Field("custid") custid: String, @Field("read") read: String,
        @Field("archive") archive: String, @Field("date") date: String,
        @Field("vc") vc: String, @Field("os") os: String,
        @Field("phonename") phoneName: String, @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<NotificationResponse>

    @POST(Constants.INBOX_ACTION_API)
    @FormUrlEncoded
    fun inboxAction(
        @Field("custid") custid: String,
        @Field("all") all: String,
        @Field("type") type: String,
        @Field("read") read: String,
        @Field("archive") archive: String,
        @Field("inbox_id") inboxid: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>



    @POST(Constants.CHANGE_PASSWORD_API)
    @FormUrlEncoded
    fun changePasswordAPI(
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("oldpass") oldPassword: String,
        @Field("newpass") newPassword: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String ,
        @Field("pvc") pv:String
    ): Response<CommonResponse>

    @POST(Constants.SET_PASSWORD_API)
    @FormUrlEncoded
    fun setPasswordAPI(
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("newpass") newPassword: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.UPLOAD_IMAGE_API)
    @FormUrlEncoded
    fun uploadImageAPI(
        @Field("custid") custId: String,
        @Field("pic") pic: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<UploadImageResponse>


    @POST(Constants.SCAN_RECEIPT_API)
    @FormUrlEncoded
    fun scanReceiptAPI(
        @Field("mall") mall: String,
        @Field("custid") custId: String,
        @Field("merchantid") merchantId: String,
        @Field("price") price: String,
        @Field("c_receipt_id") receiptId: String,
        @Field("c_receipt_date") receiptDate: String,
        @Field("c_amount") receiptAmount: String,
        @Field("c_merchant") receiptMerchant: String,
        @Field("pic_name") picName: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.MERCHANT_BRANCH_API)
    @FormUrlEncoded
    fun shopAPI(
        @Field("userid") merchantId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field(
            "os"
        ) os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field(
            "sectoken"
        ) sectoken: String,
        @Field("svc") sv: String
    ): Response<ShopResponse>



    @POST(Constants.GET_SETTINGS_API)
    @FormUrlEncoded
    fun getSettingsAPI(
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("custid") custId: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("os") os: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<GetSettingsResponse>


    @POST(Constants.UPDATE_SETTINGS_API)
    @FormUrlEncoded
    fun updateSettingsAPI(
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("custid") custId: String,
        @Field("setting") setting: String,
        @Field("general") general: String,
        @Field("news") news: String,
        @Field("righthere") righthere: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("os") os: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.CREATE_TICKET_API)
    @FormUrlEncoded
    fun supportAPI(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("subject") subject: String,
        @Field("IssueType") issueType: String,
        @Field("description") description: String,
        @Field("image") image: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<SupportResponse>


    @POST(Constants.TRANSACTION_HISTORY_API)
    @FormUrlEncoded
    fun transactionAPI(
        @Field("custid") custId: String,
        @Field("archive") archive: String,
        @Field("merchantid") merchantId: String,
        @Field("month") month: String,
        @Field("year") year: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<TransactionResponse>


    @POST(Constants.TRANSACTION_HISTORY_DETAIL_API)
    @FormUrlEncoded
    fun transactionDetailAPI(
        @Field("type") type: String,
        @Field("transid") transactionId: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<TransactionDetailResponse>


    @POST(Constants.RELEASE_POINTS_API)
    @FormUrlEncoded
    fun releasePoints(
        @Field("receipt_no") reciptNo: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>

    @POST(Constants.ARCHIVE_TRANSACTION_API)
    @FormUrlEncoded
    fun archiveTransaction(
        @Field("transid") transId: String,
        @Field("archive") archive: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<CommonResponse>


    @POST(Constants.WALLET_MULTIPLE_API)
    @FormUrlEncoded
    fun walletMultipleAPI(
        @Field("mall") mall: String,
        @Field("custid") custId: String,
        @Field("mercid") merchantId: String,
        @Field("type") walletType: String,
        @Field("id") id: String,
        @Field("condition") condition: String,
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("lang") lang: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String
    ): Response<WalletMultipleResponse>


    @POST(Constants.CHECK_MIGRATED_USER_API)
    @FormUrlEncoded
    fun checkMigratedUser(
        @Field("vc") vc: String,
        @Field("date") date: String,
        @Field("type") type: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("svc") sv: String
    ): Response<CheckMigrateUserResponse>


    @POST(Constants.GET_MIGRATED_DATA_API)
    @FormUrlEncoded
    fun getMigratedUserData(
        @Field("vc") vc: String,
        @Field("date") date: String,
        @Field("member_card_no") cardNo: String,
        @Field("id_type") idType: Int,
        @Field("nric") nric: String,
        @Field("phone_country") phoneCountry: String,
        @Field("phone") phone: String,
        @Field("svc") sv: String ,
        @Field("pvc") pvc:String
    ): Response<MigratedUserDataResponse>



    @POST(Constants.SIGN_UP_API)
    @FormUrlEncoded
    fun signUpAPI(
        @Field("token") fcmToken: String,
        @Field("member_card_no") cardNo: String,
        @Field("migrate") migrate: Int,
        @Field("referral_code") referral: String,
        @Field("promo") promo: Boolean,
        @Field("email") email: String,
        @Field("pass") password: String,
        @Field("title") title: String,
        @Field("fname") firstName: String,
        @Field("lname") lastName: String,
        @Field("phone") phone: String,
        @Field("phone_country") phoneCountry: String,
        @Field("socialmediatype") socialType: String,
        @Field("socialmediatoken") socialToken: String,
        @Field("vc") vc: String,
        @Field("date") date: String,
        @Field("lang") lang: String,
        @Field("os") os: String,
        @Field("deviceid") deviceid: String,
        @Field("devicetype") deviceType: String,
        @Field("svc") sv: String,
        @Field("pvc") pv:String
    ): Response<RegisterResponse>

    @POST(Constants.HONORIFIC_LIST_API)
    @FormUrlEncoded
    fun getHonorificList(
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<HonorificListResponse>


    @POST(Constants.COUNTRY_LIST_API)
    @FormUrlEncoded
    fun getCountryList(
        @Field("date") date: String,
        @Field("vc") vc: String,
        @Field("os") os: String,
        @Field("phonename") phoneName: String,
        @Field("phonetype") phoneType: String,
        @Field("sectoken") sectoken: String,
        @Field("svc") sv: String
    ): Response<CountryListResponse>


}