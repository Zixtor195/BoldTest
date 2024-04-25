package com.example.boldtest.application.data.network

import com.example.boldtest.application.data.repositories.autocomplete.AutocompleteApiRepository
import com.example.boldtest.application.data.repositories.autocomplete.AutocompleteApiRepositoryInterface
import com.example.boldtest.application.data.repositories.forecast.ForecastApiRepository
import com.example.boldtest.application.data.repositories.forecast.ForecastApiRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppApiProvider {

    @Binds
    @Singleton
    abstract fun providerAutocompleteApiRepository(
        autocompleteApiRepository: AutocompleteApiRepository,
    ): AutocompleteApiRepositoryInterface

    @Binds
    @Singleton
    abstract fun providerForecastApiRepository(
        forecastApiRepository: ForecastApiRepository,
    ): ForecastApiRepositoryInterface


}