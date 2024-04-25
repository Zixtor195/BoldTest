package com.example.boldtest.application.data.api

import com.example.boldtest.application.data.models.forecast.GetForecastResponse
import com.example.boldtest.utils.Constants.DAYS_STRING
import com.example.boldtest.utils.Constants.KEY_STRING
import com.example.boldtest.utils.Constants.SEARCH_TEXT
import com.example.boldtest.utils.Constants.URL_GET_FORECAST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET(URL_GET_FORECAST)
    suspend fun getForecastInfo(
        @Query(KEY_STRING) key: String,
        @Query(SEARCH_TEXT) placeName: String,
        @Query(DAYS_STRING) numberOfDays: Int
    ): Response<GetForecastResponse>
}