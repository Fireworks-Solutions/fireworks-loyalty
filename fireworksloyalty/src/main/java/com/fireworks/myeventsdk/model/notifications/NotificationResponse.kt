package com.fireworks.myeventsdk.model.notifications

import com.google.gson.annotations.SerializedName

data class NotificationResponse (

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("private_inbox_unread")
    val privateUnread: Int? = null,

    @field:SerializedName("global_inbox_unread")
    val globalUnread: Int? = null,

    @field:SerializedName("private_inbox")
    val privateInbox: ArrayList<Data>? = null,

    @field:SerializedName("global_inbox")
    val globalInbox: ArrayList<Data>? = null
)