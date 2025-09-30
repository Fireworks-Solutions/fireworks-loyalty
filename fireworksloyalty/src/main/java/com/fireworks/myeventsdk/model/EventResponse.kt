package com.fireworks.myeventsdk.model

data class EventResponse(
    val status: String,
    val message: String?,
    val Featuredevents: List<Event>,
    val events: List<Event>
)