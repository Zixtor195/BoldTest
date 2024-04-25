package com.example.boldtest.application.data.repositories.autocomplete

import com.example.boldtest.application.data.api.AutocompleteApi
import com.example.boldtest.application.data.models.autocomplete.LocationResponse
import com.example.boldtest.utils.BaseApiResponse
import com.example.boldtest.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AutocompleteApiRepository @Inject constructor(
    private val autocompleteApi: AutocompleteApi
) : AutocompleteApiRepositoryInterface, BaseApiResponse() {

    override suspend fun getAutocompleteList(
        key: String,
        textToComplete: String
    ): Flow<NetworkResult<List<LocationResponse>>> {
        return flow {
            emit(safeApiCall {
                autocompleteApi.getAutocompleteList(key, textToComplete)
            })
        }.flowOn(Dispatchers.IO)
    }
}