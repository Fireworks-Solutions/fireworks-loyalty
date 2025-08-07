package com.fireworks.myeventsdk.model

data class Event(
    val id: String,
    val name: String,
    val description: String,
    val quantity: String,
    val point: String,
    val img: String,
    val date: String,
    val expiry_date: String,
    val is_rsvp: String
)