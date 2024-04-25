package com.example.boldtest.application.usecase.autocomplete

import com.example.boldtest.application.data.models.autocomplete.LocationResponse
import com.example.boldtest.application.data.repositories.autocomplete.AutocompleteApiRepositoryInterface
import com.example.boldtest.utils.Constants.WEATHER_API_KEY
import com.example.boldtest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAutocompleteListUseCase @Inject constructor(
    private val autocompleteApiRepository: AutocompleteApiRepositoryInterface,
) {

    suspend operator fun invoke(textToComplete: String): Flow<NetworkResult<List<LocationResponse>>> {
        return autocompleteApiRepository.getAutocompleteList(WEATHER_API_KEY, textToComplete)
    }

}