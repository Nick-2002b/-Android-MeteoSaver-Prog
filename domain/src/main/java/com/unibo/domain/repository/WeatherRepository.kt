package com.unibo.domain.repository

import com.unibo.domain.model.Weather
import kotlinx.coroutines.flow.StateFlow

interface WeatherRepository {
    val weatherList: StateFlow<List<Weather>>
    fun fetchRemoteWeatherByCity(cityName: String)
    fun refreshAllCities()
    fun deleteCity(cityName: String)
}