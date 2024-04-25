package com.example.boldtest.utils.application

import android.content.Context
import com.example.boldtest.application.data.api.AutocompleteApi
import com.example.boldtest.application.data.api.ForecastApi
import com.example.boldtest.utils.Constants.URL_WEATHER_API
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseApplicationProvider {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    @Named("weather_api")
    fun provideWeatherApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_WEATHER_API)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideAutocompleteApiClient(
        @Named("weather_api") retrofit: Retrofit
    ): AutocompleteApi = retrofit.create(AutocompleteApi::class.java)

    @Singleton
    @Provides
    fun provideForecastApiClient(
        @Named("weather_api") retrofit: Retrofit
    ): ForecastApi = retrofit.create(ForecastApi::class.java)

}