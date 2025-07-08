package com.fireworks.myeventsdk.model.events_detail

import com.fireworks.myeventsdk.model.rewards_search.SearchRewardItem
import digital.fireworks.kpdrm.data.dto.events_detail.Detail

data class EventsDetailResponse(
    val custname: String,
    val details: List<Detail>,
    val status: String,
    val message:String? = ""
)
fun EventsDetailResponse.getSearchList(): List<SearchRewardItem> {
    return this.getSearchList() // or details, etc., depending on your model
}
