package com.fireworks.myeventsdk.model.promotions_news

import com.fireworks.myeventsdk.model.rewards_search.SearchRewardItem
import digital.fireworks.kpdrm.data.dto.promotions_news.New

data class PromotionNewsResponse(
    val news: List<New>,
    val status: String,
    val total: Int,
    val message:String? = ""
)

fun PromotionNewsResponse.getSearchList():List<SearchRewardItem>{
    return news.map {
        SearchRewardItem(
            itemTitle = it.title,
            itemId = it.id,
        )
    }
}