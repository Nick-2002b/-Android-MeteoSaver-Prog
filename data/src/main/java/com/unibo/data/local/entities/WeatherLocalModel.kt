package com.unibo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_locations")
data class WeatherLocalModel(
    @PrimaryKey val cityName: String,
    val icon: String,
    val weatherDescription: String,
    val temperature: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Double,
    val windSpeed: Double,
    val feelsLike: Double,
    val lastUpdate: Long = System.currentTimeMillis()
)
