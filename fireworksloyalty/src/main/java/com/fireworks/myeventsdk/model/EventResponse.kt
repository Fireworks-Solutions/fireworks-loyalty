package com.fireworks.myeventsdk.model

data class EventResponse(
    val status: String,
    val message: String?,
    val featured_events: List<Event>,
    val events: List<Event>
)