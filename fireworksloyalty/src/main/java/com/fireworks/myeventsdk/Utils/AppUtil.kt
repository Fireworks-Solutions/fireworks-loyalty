package com.fireworks.myeventsdk.Utils

import android.content.Context
import com.incredibleqr.mysogo.data.remote.model.dashboard.DashboardResponse

class AppUtil {

    companion object {
        var applicationToken = ""
        var language = "en"
        var dashboardResponse: DashboardResponse? = null
        var currentMall = 0
        var latitude = 0.0
        var longitude = 0.0
        var mall = 0
        fun getDeviceId(context: Context): String {
            return ""
        }
    }


}