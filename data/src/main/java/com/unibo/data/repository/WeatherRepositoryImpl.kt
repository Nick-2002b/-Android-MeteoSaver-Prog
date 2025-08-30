package com.unibo.data.repository

import com.unibo.data.remote.WeatherApiService
import com.unibo.domain.model.Weather
import com.unibo.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(private val weatherApiService: WeatherApiService) : WeatherRepository {
    override suspend fun getRemoteWeatherByCity(cityName: String): Result<Weather> {
        TODO("Not yet implemented")
    }

    override fun getSavedWeather(): Flow<List<Weather>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveWeatherLocation(weather: Weather) {
        TODO("Not yet implemented")
    }
}