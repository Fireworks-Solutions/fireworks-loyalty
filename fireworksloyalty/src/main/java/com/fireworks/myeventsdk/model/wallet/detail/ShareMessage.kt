package com.fireworks.myeventsdk.model.wallet.detail

import com.google.gson.annotations.SerializedName

/**
 * @project:      Asterspring
 * @package:      my.fireworks.pohkong.data.remote.model.wallet.detail
 * @version:      1.0
 * @author:       Dilip <dilipkumar4813@gmail.com>
 * @description:  description
 * @since:        25/08/2019 13:30
 */

data class ShareMessage(

@field:SerializedName("to")
val to: String? = null,

@field:SerializedName("notes")
val notes: String? = null
)