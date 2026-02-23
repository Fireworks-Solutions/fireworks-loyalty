package com.fireworks.myeventsdk.model

import com.fireworks.myeventsdk.model.news.NewsItem
import com.google.gson.annotations.SerializedName

data class ListSupplementaryResponse (
    val status: String?,
    val customers: List<SupplementaryCustomer>?
)