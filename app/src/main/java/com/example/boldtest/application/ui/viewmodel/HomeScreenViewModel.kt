package com.example.boldtest.application.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.boldtest.application.data.models.autocomplete.LocationResponse
import com.example.boldtest.application.data.models.forecast.GetForecastResponse
import com.example.boldtest.application.ui.viewmodel.states.ForecastDay
import com.example.boldtest.application.ui.viewmodel.states.ForecastInformation
import com.example.boldtest.application.usecase.autocomplete.GetAutocompleteListUseCase
import com.example.boldtest.application.usecase.forecast.GetForecastInfoUseCase
import com.example.boldtest.utils.Constants.EMPTY_STRING
import com.example.boldtest.utils.Constants.FORECAST_DAYS
import com.example.boldtest.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAutocompleteListUseCase: GetAutocompleteListUseCase,
    private val getForecastInfoUseCase: GetForecastInfoUseCase,
    app: Application
) : AndroidViewModel(app) {

    private val _autocompleteList: MutableLiveData<NetworkResult<List<LocationResponse>>> =
        MutableLiveData(NetworkResult.Loading())
    private val autocompleteList: LiveData<NetworkResult<List<LocationResponse>>> =
        _autocompleteList

    private val _forecastInfo: MutableLiveData<NetworkResult<GetForecastResponse>> =
        MutableLiveData(NetworkResult.Loading())
    private val forecastInfo: LiveData<NetworkResult<GetForecastResponse>> =
        _forecastInfo

    private val _textToComplete = MutableStateFlow(EMPTY_STRING)
    val textToComplete: StateFlow<String> = _textToComplete.asStateFlow()

    private val _locationList = MutableStateFlow(emptyList<String>())
    val locationList: StateFlow<List<String>> = _locationList.asStateFlow()

    private val _forecastInformation = MutableStateFlow(ForecastInformation())
    val forecastInformation: StateFlow<ForecastInformation> = _forecastInformation.asStateFlow()

    private fun getAutocompleteList(textToComplete: String) {

        viewModelScope.launch {
            _autocompleteList.value = NetworkResult.Loading()
            getAutocompleteListUseCase.invoke(textToComplete).collect { values ->
                _autocompleteList.value = values

                when (autocompleteList.value) {
                    is NetworkResult.Success -> {
                        val locationsList = arrayListOf<String>()
                        autocompleteList.value?.data?.let {
                            it.forEach { location ->
                                locationsList.add(location.locationName)
                            }
                            locationListModifier(locationsList)
                        }
                        Log.i("HomeScreenViewModel", "GetAutocompleteList Work on Success")
                    }

                    is NetworkResult.Loading -> {
                        Log.i("HomeScreenViewModel", "GetAutocompleteList Work on Loading")
                    }

                    is NetworkResult.Error -> {
                        locationListModifier(emptyList())

                        Log.e(
                            "HomeScreenViewModel",
                            "GetAutocompleteList Work on error: " + autocompleteList.value?.message
                        )
                    }
                }
            }
        }
    }

    private fun getForecastInfo(locationName: String) {
        viewModelScope.launch {
            _forecastInfo.value = NetworkResult.Loading()
            getForecastInfoUseCase.invoke(locationName, FORECAST_DAYS).collect { values ->
                _forecastInfo.value = values

                when (forecastInfo.value) {
                    is NetworkResult.Success -> {
                        val forecastDayList = arrayListOf<ForecastDay>()

                        forecastInfo.value?.data?.let {

                            it.forecast.forecastDayList.forEach { forecastDay ->

                                forecastDayList.add(
                                    ForecastDay(
                                        date = forecastDay.forecastDate,
                                        averageTemperature = forecastDay.forecastDay.forecastAverageTemperature,
                                        conditionText = forecastDay.forecastDay.forecastCondition.conditionText,
                                        conditionIcon = "https:" + forecastDay.forecastDay.forecastCondition.conditionIcon,
                                    )
                                )
                            }

                            val forecastInformation = ForecastInformation(
                                country = it.location.locationCountry,
                                name = it.location.locationName,
                                forecastDayList = forecastDayList
                            )

                            forecastInformationModifier(forecastInformation)

                        }

                        Log.i("HomeScreenViewModel", "GetForecastInfo Work on Success")
                    }

                    is NetworkResult.Loading -> {
                        Log.i("HomeScreenViewModel", "GetForecastInfo Work on Loading")
                    }

                    is NetworkResult.Error -> {
                        Log.e(
                            "HomeScreenViewModel",
                            "GetForecastInfo Work on error: " + forecastInfo.value?.message
                        )
                    }
                }
            }
        }
    }

    fun isExpandedAutocompleteDropDownMenu(): Boolean {
        return locationList.value.isNotEmpty()
    }

    fun onSearchTextFieldChange(text: String) {
        textToCompleteModifier(text)
        getAutocompleteList(text)
    }

    fun onSelectLocationName(locationName: String) {
        textToCompleteModifier(locationName)
        locationListModifier(emptyList())
        getForecastInfo(locationName)
    }

    private fun locationListModifier(
        list: List<String> = locationList.value
    ) {
        _locationList.value = list
    }

    private fun textToCompleteModifier(
        text: String = textToComplete.value
    ) {
        _textToComplete.value = text
    }

    private fun forecastInformationModifier(
        forecastInfo: ForecastInformation = forecastInformation.value
    ) {
        _forecastInformation.value = forecastInfo
    }
}