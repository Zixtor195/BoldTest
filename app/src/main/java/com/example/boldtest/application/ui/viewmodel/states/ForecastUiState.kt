package com.example.boldtest.application.ui.viewmodel.states

data class ForecastInformation(
    val name: String = "",
    val country: String = "",
    val forecastDayList: List<ForecastDay> = emptyList()

)

data class ForecastDay(
    val date: String = "",
    val averageTemperature: String = "",
    val conditionText: String = "",
    val conditionIcon: String = ""
)