package com.fireworks.myeventsdk.model


data class SupplementaryCustomer(
    val custid: String?,
    val memberid: String?,
    val fname: String?,
    val lname: String?,
    val email: String?,
    val phone: String?,
    val points_transferable: Boolean?,
    val rewards_transferable: Boolean?,
    val dob: String?
)