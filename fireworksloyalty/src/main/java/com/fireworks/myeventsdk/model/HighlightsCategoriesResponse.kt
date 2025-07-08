package com.fireworks.myeventsdk.model

data class HighlightsCategoriesResponse(
    val result: List<Result>,
    val status: String,
    val message: String? = ""
)

data class Result(
    val id: String,
    val title: String
)

