package com.fireworks.myeventsdk.model.PRIVILEGEdASH

import java.util.ArrayList

data class PrivRankResponse(
    val status: String?,
    val message: String?,
    val general: General? = null,
    val ranks: ArrayList<Ranks>? = null,
    val tiers: ArrayList<Tiers>? = null
)
data class General(
    val title: String,
    val desc: String
)

data class Eligible(
    val title: String?,
    val description: String?
)

data class Sections(
    val title: String?,
    val icon: String?,
    val description: String?
)

data class Ranks(
    val id: String?,
    val name: String?,
    val image: String?,
    val row_1: String?,
    val row_1_desc: String?,
    val row_2: String?,
    val row_2_desc: String?
)

data class Tiers(
    val id: String?,
    val name: String?,
    val title: String?,
    val card_image: String?,
    val eligibility_info: Eligible?,
    val sections: ArrayList<Sections>?
)