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

    init {
        scope.launch {
            weatherDao.getAllWeather().collect { entities ->
                _weatherList.value = entities.map { it.toDomain() }
            }
        }
    }

    override fun fetchRemoteWeatherByCity(cityName: String) {
        scope.launch {
            try {
                val response = weatherApiService.getWeather(cityName, lang = "IT")
                weatherDao.insertWeather(response.toWeatherLocalModel())
            } catch (e: Exception){
                println("Error during data fetch: ${e.message}")
            }
        }
    }

    override fun refreshAllCities() {
        scope.launch{
            try{
                val savedCities = weatherDao.getAllWeatherOnce()
                savedCities.forEach { cityEntity ->
                    val updatedDto = weatherApiService.getWeather(city = cityEntity.cityName, lang = "IT")
                    val updatedEntity = updatedDto.toWeatherLocalModel()
                    weatherDao.insertWeather(updatedEntity)
                }
            } catch (e: Exception){
                println("Errore durante il refresh di tutte le città: ${e.message}")
            }
        }
    }

    override fun deleteCity(cityName: String) {
        scope.launch {
            weatherDao.deleteCityByName(cityName)
        }
    }

    private fun Double?.toCelsius(): Double {
        val tempInFahrenheit = this ?: 32.0
        return "%.1f".format((tempInFahrenheit - 32) * 5.0/9.0).toDouble()
    }
    // Mapper to convert ApiResponse for saving into the DB
    private fun ApiResponse.toWeatherLocalModel(): WeatherLocalModel {
        return WeatherLocalModel(
            cityName = this.cityName ?: "Sconosciuta",
            icon = this.weather?.firstOrNull()?.icon?.let { iconCode -> "https://openweather.site/img/wn/$iconCode.png" }
                ?: "",
            weatherDescription = this.weather?.firstOrNull()?.description ?: "N/D",
            temperature = this.main?.temperature.toCelsius(),
            humidity = this.main?.humidity ?: 0.0,
            windSpeed = this.wind?.speed ?: 0.0,
            feelsLike = this.main?.feelsLike.toCelsius(),
            tempMin = this.main?.tempMin.toCelsius(),
            tempMax = this.main?.tempMax.toCelsius(),
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
            tempMin = this.tempMin,
            tempMax = this.tempMax,
            humidity = this.humidity,
            windSpeed = this.windSpeed,
            feelsLike = this.feelsLike,
        )
    }
}