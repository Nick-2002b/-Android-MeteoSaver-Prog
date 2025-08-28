package com.unibo.domain.repository

import com.unibo.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getRemoteWeatherByCity(cityName: String): Result<Weather>

    fun getSavedWeather(): Flow<List<Weather>>

    suspend fun saveWeatherLocation(weather: Weather)
}