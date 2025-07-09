package com.incredibleqr.mysogo.util.offline_caching

import android.annotation.SuppressLint
import com.fireworks.myeventsdk.model.offline_caching.GetJsonResponse
import io.paperdb.Paper
import java.text.SimpleDateFormat

object OfflineCacheUtils {

    fun isDataAvailable(): Boolean {
        return (GetJsonResponse.getPointsApi() != null) && (GetJsonResponse.getDashboardApi() != null) && (GetJsonResponse.getProfileApi() != null) && (GetJsonResponse.getMultiMallApi() != null)
    }

    fun isPromotionOfflineDataAvailable():Boolean{
        return GetJsonResponse.getPromotionApi() != null
    }

    fun isEventsOfflineDataAvailable():Boolean{
        return GetJsonResponse.getEventsApi()!=null
    }

    fun setCanFetchOnlineData(canFetch:Boolean ){
        Paper.book().write("fetch_online",canFetch)
    }

    fun getIfCanFetchOnlineData():Boolean{
        return Paper.book().read<Boolean>("fetch_online")?:false
    }

    fun setLastTimeProfileUpdated(time:Long){
        Paper.book().write("last_time_profile_updated",time)
    }

    private fun getProfileLastUpdatedTimeAsMilliSeconds():Long{
        return Paper.book().read("last_time_profile_updated") ?: 0L
    }

    @SuppressLint("SimpleDateFormat")
    fun getLastTimeProfileUpdatedText(): String {
        val lastTimeProfileUpdated = getProfileLastUpdatedTimeAsMilliSeconds()
        if (lastTimeProfileUpdated == 0L)
            return ""
        val df = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val formattedDate = df.format(lastTimeProfileUpdated)
        return "Last update on $formattedDate"
    }

    fun clearCache(){
        Paper.book().delete("dashboard-api")
        Paper.book().delete("multi-mall")
        Paper.book().delete("points-api")
        Paper.book().delete("profile-api")
        Paper.book().delete("promotion-api")
        Paper.book().delete("events-api")
    }
}