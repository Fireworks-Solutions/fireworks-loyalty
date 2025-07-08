package com.fireworks.myeventsdk.model

data class EventResponse(
    val status: String,
    val message: String?,
    val events: List<Event>
)