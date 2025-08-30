package com.unibo.data.remote

import com.unibo.data.remote.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("city/{city}/{lang}")
    suspend fun getWeather(
        @Query("city") city: String,
        @Query("lang") lang: String = "IT",
    ): ApiResponse
}