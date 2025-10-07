package com.fireworks.myeventsdk

import com.fireworks.myeventsdk.model.categoryid
import com.fireworks.myeventsdk.model.reward.category.Category
import com.google.gson.annotations.SerializedName

data class ArticleCategoryResponse(

    @field:SerializedName("status")
    val status: String? = null,


    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("sort_by")
    val sort_by: ArrayList<sortBy>? = null,

    @field:SerializedName("category")
    val category: ArrayList<categoryid>? = null,


    )

data class sortBy(


    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("image")
    val image: String? = null,


    @field:SerializedName("featured")
    val featured: Boolean? = null,

    )