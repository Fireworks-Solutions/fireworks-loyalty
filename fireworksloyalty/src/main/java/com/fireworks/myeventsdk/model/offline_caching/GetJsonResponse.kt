package com.incredibleqr.mysogo.util.offline_caching

import com.incredibleqr.mysogo.data.remote.model.PointDetailResponse
import com.incredibleqr.mysogo.data.remote.model.dashboard.DashboardResponse
import com.incredibleqr.mysogo.data.remote.model.event.EventResponse
import com.incredibleqr.mysogo.data.remote.model.mall.MultiMallResponse
import com.fireworks.myeventsdk.model.news.NewsResponse
import com.incredibleqr.mysogo.data.remote.model.profile.ProfileResponse
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