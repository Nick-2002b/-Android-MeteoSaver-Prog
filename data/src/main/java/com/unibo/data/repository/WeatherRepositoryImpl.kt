package com.unibo.data.repository

import android.content.Context
import com.unibo.data.local.db.AppDatabase
import com.unibo.data.local.entities.WeatherLocalModel
import com.unibo.data.remote.WeatherApiService
import com.unibo.data.remote.models.ApiResponse
import com.unibo.domain.model.Weather
import com.unibo.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherRepositoryImpl(
    private val weatherApiService: WeatherApiService,
    context: Context
) : WeatherRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _weatherList = MutableStateFlow<List<Weather>>(listOf())
    override val weatherList: StateFlow<List<Weather>> = _weatherList

    private val weatherDao = AppDatabase.getInstance(context = context).weatherDao()

    override suspend fun fetchRemoteWeatherByCity(cityName: String) {
        scope.launch {
            try {
                val response = weatherApiService.getWeather(cityName, lang = "IT")
                weatherDao.insertWeather(response.toEntity())
            } catch (e: Exception){
                println("Error during data fetch: ${e.message}")
            }
        }
    }

    // Mapper to convert ApiResponse for saving into the DB
    private fun ApiResponse.toEntity(): WeatherLocalModel {
        return WeatherLocalModel(
            cityName = this.cityName ?: "Sconosciuta",
            icon = this.weather?.firstOrNull()?.icon?.let { iconCode -> "https://openweather.site/img/wn/$iconCode.png" }?: "",
            weatherDescription = this.weather?.firstOrNull()?.description ?: "N/D",
            temperature = this.main?.temperature ?: 0.0,
            humidity = this.main?.humidity ?: 0.0,
            windSpeed = this.wind?.speed ?: 0.0,
            feelsLike = this.main?.feelsLike ?: 0.0,
            lastUpdate = System.currentTimeMillis(),
        )
    }

    // Mapper to convert WeatherLocalModel to Weather Model for the UI
    private fun WeatherLocalModel.toDomain(): Weather {
        return Weather(
            cityName = this.cityName,
            icon = this.icon,
            weatherDescription = this.weatherDescription,
            temperature = this.temperature,
            humidity = this.humidity,
            windSpeed = this.windSpeed,
            feelsLike = this.feelsLike,
        )
    }
}