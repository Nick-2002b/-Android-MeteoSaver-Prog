package com.unibo.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "speed") val speed: Double? = null,
)
