package com.incredibleqr.mysogo.data.remote.model.country_codes

data class CountryCodesResponse(
    val country_codes: List<String>,
    val country_display: List<String>,
    val message: String,
    val status: String
)