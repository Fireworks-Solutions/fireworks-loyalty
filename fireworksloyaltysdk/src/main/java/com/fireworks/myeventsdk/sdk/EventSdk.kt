package com.fireworks.myeventsdk.sdk

import com.fireworks.myeventsdk.model.Event
import com.fireworks.myeventsdk.model.rewards_search.SearchRewardItem
import digital.fireworks.kpdrm.data.dto.events_detail.Detail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EventSdk {

    interface Callback {
        fun onSuccess(events: List<Event>)
        fun onFailure(errorMessage: String)
    }

    interface EventDetailCallback {
        fun onSuccess(detail: Detail?)
        fun onFailure(errorMessage: String)
    }

    interface CategoryCallback {
        fun onSuccess(categories: List<Result>)
        fun onFailure(errorMessage: String)
    }

    interface SearchCallback {
        fun onSuccess(results: List<SearchRewardItem>) // <-- Your actual model
        fun onFailure(errorMessage: String)
    }




    private lateinit var retrofitService: Service

    fun init(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(Service::class.java)
    }

    fun fetchEvents(
        sectoken: String,
        custId: String,
        vcKey: String,
        category: String? = "0",
        searchTerm: String? = "",
        mall: String = "12",
        isLatest: String = "1",
        callback: Callback
    ) {
        val date = System.currentTimeMillis().toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getEvents(
                    sectoken = sectoken,
                    category = category,
                    searchItem = searchTerm,
                    mall = mall,
                    isLatest = isLatest,
                    custID = custId,
                    date = date,
                    vc = vcKey
                )

                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main) {
                        callback.onSuccess(response.body()!!.events)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        callback.onFailure("API failed with status: ${response.code()}")
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("Error: ${e.localizedMessage}")
                }
            }
        }
    }


    fun fetchEventCategories(
        secToken: String,
        customerId: String,
        callback: CategoryCallback
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getEventsCategories(secToken, customerId)
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main) {
                        callback.onSuccess(response.body()!!.result)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        callback.onFailure("Category API failed: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure("Category error: ${e.localizedMessage}")
                }
            }
        }
    }


    fun fetchEventDetail(
        eventId: String,
        token: String,
        custId: String,
        vc: String,
        callback: EventDetailCallback
    ) {
        val date = System.currentTimeMillis().toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitService.getEventsDetails(
                    secToken = token,
                    event_id = eventId,
                    customer_id = custId,
                    vc = vc,
                    date = date,
                    mall = 12,
                )

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!
                    withContext(Dispatchers.Main) {
                        if (body.status.equals("failed", ignoreCase = true)) {
                            callback.onFailure(body.message ?: "Unknown error")
                        } else {
                            callback.onSuccess(body.details.firstOrNull())
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        callback.onFailure("API failed: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(e.localizedMessage ?: "Network error")
                }
            }
        }
    }




}