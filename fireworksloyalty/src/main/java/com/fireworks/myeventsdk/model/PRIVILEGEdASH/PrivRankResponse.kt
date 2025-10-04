package com.fireworks.myeventsdk.model.PRIVILEGEdASH

import java.util.ArrayList

data class PrivRankResponse(
    val status: String,
    val message: String,
    val general: General,
    val ranks: ArrayList<Ranks>,

)
data class General(
    val title: String,
    val desc: String,
)

data class Ranks(
    val id: String,
    val name: String,
    val image: String,
    val row_1: String,
    val row_1_desc: String,
    val row_2: String,
    val row_2_desc: String,

    )