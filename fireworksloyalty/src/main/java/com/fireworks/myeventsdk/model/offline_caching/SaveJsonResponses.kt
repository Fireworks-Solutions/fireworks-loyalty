package com.fireworks.myeventsdk.model.offline_caching

import com.fireworks.myeventsdk.model.EventResponse
import com.fireworks.myeventsdk.model.PointDetailResponse
import com.fireworks.myeventsdk.model.Profile.ProfileResponse
import com.fireworks.myeventsdk.model.dashboard.DashboardResponse
import com.incredibleqr.mysogo.data.remote.model.mall.MultiMallResponse
import com.fireworks.myeventsdk.model.news.NewsResponse
import io.paperdb.Paper

object SaveJsonResponses {

    fun saveMultiMallApi(multiMallResponse: MultiMallResponse?) {
        if (multiMallResponse != null) {
            Paper.book().write("multi-mall", multiMallResponse)
        }
    }

    fun saveDashboardApi(dashboard: DashboardResponse?) {
        if (dashboard != null) {
            Paper.book().write("dashboard-api", dashboard)
        }
    }

    fun savePointsApi(points: PointDetailResponse?) {
        if (points != null) {
            Paper.book().write("points-api", points)
        }
    }

    fun saveProfileApi(profile: ProfileResponse?) {
        if (profile != null) {
            Paper.book().write("profile-api", profile)
        }
    }

    fun savePromotionApi(promotion:NewsResponse){
        Paper.book().write("promotion-api", promotion)
    }

    fun saveEventsApi(events: EventResponse){
        Paper.book().write("events-api",events)
    }

}