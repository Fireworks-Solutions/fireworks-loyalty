package com.fireworks.myeventsdk.model

data class EventResponse(
    val status: String,
    val message: String?,
    val title_1: String?,
    val title_2: String?,
    val featured_events: List<Event>,
    val events: List<Event>
)