package com.fireworks.myeventsdk.model

import com.fireworks.myeventsdk.model.news.NewsItem
import com.google.gson.annotations.SerializedName

data class StoreLocatorFilterResponse (
    val status: String?,
    val states: List<states>?,
    val services: List<services>?
)

data class states(
    val id: String?,
    val name: String?
)

data class services(
    val id: String?,
    val name: String?,
    val icon: String?
)