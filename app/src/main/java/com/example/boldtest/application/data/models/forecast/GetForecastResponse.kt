package com.example.boldtest.application.data.models.forecast

import com.google.gson.annotations.SerializedName

class GetForecastResponse(
    @SerializedName("location") val location: Location = Location(),
    @SerializedName("forecast") val forecast: Forecast = Forecast(),
)

class Location(
    @SerializedName("name") val locationName: String = "",
    @SerializedName("country") val locationCountry: String = "",
)

class Forecast(
    @SerializedName("forecastday") val forecastDayList: List<ForecastDay> = emptyList(),
)

class ForecastDay(
    @SerializedName("date") val forecastDate: String = "",
    @SerializedName("day") val forecastDay: Day = Day(),
)

class Day(
    @SerializedName("avgtemp_c") val forecastAverageTemperature: String = "",
    @SerializedName("condition") val forecastCondition: Condition = Condition(),
)

class Condition(
    @SerializedName("text") val conditionText: String = "",
    @SerializedName("icon") val conditionIcon: String = "",
)