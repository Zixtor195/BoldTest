package com.example.boldtest.application.data.repositories.forecast

import com.example.boldtest.application.data.models.forecast.GetForecastResponse
import com.example.boldtest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ForecastApiRepositoryInterface {

    suspend fun getForecastInfo(
        key: String,
        locationName: String,
        numberOfDays: Int
    ): Flow<NetworkResult<GetForecastResponse>>
}