package com.unibo.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherInfo(
    @Json(name = "description") val description: String? = null,
    @Json(name = "icon") val icon: String? = null,
)
