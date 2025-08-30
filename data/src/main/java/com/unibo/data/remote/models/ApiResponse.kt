package com.unibo.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "weather") val weather: List<WeatherInfo>? = listOf(),
    @Json(name = "main") val main: Main? = Main(),
    @Json(name = "wind") val wind: Wind? = Wind(),
    @Json(name = "name") val cityName: String? = null,
 )