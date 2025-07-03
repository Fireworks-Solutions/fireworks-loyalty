package com.fireworks.myeventsdk.sdk

import android.database.Observable
import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.Utils.NetworkUtils
import com.fireworks.myeventsdk.model.events_detail.EventsDetailResponse
import com.fireworks.myeventsdk.model.events_detail.EventsResponse
import com.fireworks.myeventsdk.model.promotions_news.PromotionNewsResponse
import com.fireworks.myeventsdk.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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


}