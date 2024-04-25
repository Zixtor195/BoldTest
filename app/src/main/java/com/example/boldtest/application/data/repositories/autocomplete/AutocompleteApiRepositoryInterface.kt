package com.example.boldtest.application.data.repositories.autocomplete

import com.example.boldtest.application.data.models.autocomplete.LocationResponse
import com.example.boldtest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AutocompleteApiRepositoryInterface {

    suspend fun getAutocompleteList(
        key: String,
        textToComplete: String
    ): Flow<NetworkResult<List<LocationResponse>>>

}