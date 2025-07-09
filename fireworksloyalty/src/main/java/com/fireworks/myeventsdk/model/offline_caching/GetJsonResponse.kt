package com.fireworks.myeventsdk.model.offline_caching

import com.fireworks.myeventsdk.model.EventResponse
import com.fireworks.myeventsdk.model.PointDetailResponse
import com.fireworks.myeventsdk.model.Profile.ProfileResponse
import com.fireworks.myeventsdk.model.dashboard.DashboardResponse
import com.incredibleqr.mysogo.data.remote.model.mall.MultiMallResponse
import com.fireworks.myeventsdk.model.news.NewsResponse
import io.paperdb.Paper

object GetJsonResponse {

    fun getMultiMallApi(): MultiMallResponse? {
        return Paper.book().read("multi-mall")
    }

    fun getDashboardApi():DashboardResponse?{
        return Paper.book().read("dashboard-api")
    }

    fun getPointsApi(): PointDetailResponse? {
        return Paper.book().read("points-api")
    }

    fun getProfileApi(): ProfileResponse? {
        return Paper.book().read("profile-api")
    }

    fun getPromotionApi(): NewsResponse? {
        return Paper.book().read("promotion-api")
    }

    fun getEventsApi(): EventResponse? {
        return Paper.book().read("events-api")
    }
}