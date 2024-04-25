package com.example.boldtest.application.data.api

import com.example.boldtest.application.data.models.autocomplete.LocationResponse
import com.example.boldtest.utils.Constants.KEY_STRING
import com.example.boldtest.utils.Constants.SEARCH_TEXT
import com.example.boldtest.utils.Constants.URL_GET_AUTOCOMPLETE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AutocompleteApi {

    @GET(URL_GET_AUTOCOMPLETE)
    suspend fun getAutocompleteList(
        @Query(KEY_STRING) key: String,
        @Query(SEARCH_TEXT) textToComplete: String
    ): Response<List<LocationResponse>>

}