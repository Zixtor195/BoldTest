package com.example.boldtest.application.data.repositories.forecast

import com.example.boldtest.application.data.api.ForecastApi
import com.example.boldtest.application.data.models.forecast.GetForecastResponse
import com.example.boldtest.utils.BaseApiResponse
import com.example.boldtest.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ForecastApiRepository @Inject constructor(
    private val forecastApi: ForecastApi
) : ForecastApiRepositoryInterface, BaseApiResponse() {

    override suspend fun getForecastInfo(
        key: String,
        locationName: String,
        numberOfDays: Int
    ): Flow<NetworkResult<GetForecastResponse>> {
        return flow {
            emit(safeApiCall {
                forecastApi.getForecastInfo(key, locationName, numberOfDays)
            })
        }.flowOn(Dispatchers.IO)
    }
}