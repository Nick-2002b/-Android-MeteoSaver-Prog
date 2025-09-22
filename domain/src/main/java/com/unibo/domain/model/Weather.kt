package com.unibo.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class Weather (
    val cityName: String,
    val icon: String,
    val weatherDescription: String,
    val temperature: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Double,
    val windSpeed: Double,
    val feelsLike: Double,
)