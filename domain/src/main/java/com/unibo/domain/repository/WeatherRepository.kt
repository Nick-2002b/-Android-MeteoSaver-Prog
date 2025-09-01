package com.unibo.domain.repository

import com.unibo.domain.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface WeatherRepository {
    val weatherList: StateFlow<List<Weather>>

    suspend fun getRemoteWeatherByCity(cityName: String)

    fun getSavedWeather(): Flow<List<Weather>>

    suspend fun saveWeatherLocation(weather: Weather)
}