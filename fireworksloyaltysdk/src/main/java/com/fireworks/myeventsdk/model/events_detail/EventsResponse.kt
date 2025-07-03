package com.fireworks.myeventsdk.model.events_detail

import android.health.connect.datatypes.ExerciseRoute.Location
import com.fireworks.myeventsdk.model.Event
import com.fireworks.myeventsdk.model.rewards_search.SearchRewardItem


data class EventsResponse(
    val custname: String,
    val events: List<Event>,
    val location: List<Location>,
    val points: String,
    val status: String,
    val total: Int,
    val message:String? = ""
)

fun EventsResponse.getSearchList():List<SearchRewardItem>{
    return events.map {
        SearchRewardItem(
            itemTitle = it.name,
            itemId = it.id,
        )
    }
}