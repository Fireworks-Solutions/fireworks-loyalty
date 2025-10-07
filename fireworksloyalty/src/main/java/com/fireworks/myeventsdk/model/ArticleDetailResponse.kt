package com.fireworks.myeventsdk.model

import com.google.gson.annotations.SerializedName

data class ArticleDetailResponse(

    @field:SerializedName("status")
    val status: String? = null,


    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("article")
    val article: Artic? = null
)

data class Artic(


    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val description: String? = null,


    @field:SerializedName("date")
    val date: String? = null,


    @field:SerializedName("featured_img")
    val featured_img: String? = null,

    @field:SerializedName("external_link")
    val external_link: String? = null,

    @field:SerializedName("category_id")
    val category_id: Int? = null,

    @field:SerializedName("category_name")
    val category_name: String? = null,

    )