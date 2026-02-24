package com.fireworks.myeventsdk.model

import com.fireworks.myeventsdk.model.news.NewsItem
import com.google.gson.annotations.SerializedName

data class StoreLocatorListResponse (
    val status: String?,
    val outlets: List<outlets>?
)

data class outlets(
    val id: String?,
    val title: String?,
    val phone_no: String?,
    val email: String?,
    val mobile_no: String?,
    val latitude: String?,
    val longitude: String?,
    val address: String?,
    val images: List<String>?

)

