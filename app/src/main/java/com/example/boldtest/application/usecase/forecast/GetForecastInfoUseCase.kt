package com.example.boldtest.application.usecase.forecast

import com.example.boldtest.application.data.models.forecast.GetForecastResponse
import com.example.boldtest.application.data.repositories.forecast.ForecastApiRepositoryInterface
import com.example.boldtest.utils.Constants.WEATHER_API_KEY
import com.example.boldtest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetForecastInfoUseCase @Inject constructor(
    private val forecastApiRepositoryInterface: ForecastApiRepositoryInterface,
) {

    suspend operator fun invoke(
        locationName: String,
        numberOfDays: Int
    ): Flow<NetworkResult<GetForecastResponse>> {
        return forecastApiRepositoryInterface.getForecastInfo(
            WEATHER_API_KEY,
            locationName,
            numberOfDays
        )
    }

}