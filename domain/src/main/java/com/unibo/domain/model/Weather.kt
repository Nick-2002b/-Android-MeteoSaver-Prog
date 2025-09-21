package com.unibo.domain.model

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