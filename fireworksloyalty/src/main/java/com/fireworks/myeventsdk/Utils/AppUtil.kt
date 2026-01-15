package com.fireworks.myeventsdk.Utils

import android.content.Context
import android.provider.Settings
import com.fireworks.myeventsdk.model.dashboard.DashboardResponse

class AppUtil {

    companion object {
//        var applicationToken = ""
        var language = "en"
        var dashboardResponse: DashboardResponse? = null
        var latitude = 0.0
        var longitude = 0.0
        var mall = 0
        fun getDeviceId(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

        }
    }

}