package com.unibo.data.di

import com.unibo.data.remote.RetrofitClient
import com.unibo.data.repository.WeatherRepositoryImpl
import com.unibo.domain.di.RepositoryProvider
import com.unibo.domain.repository.WeatherRepository

class RepositoryProviderImpl: RepositoryProvider {
    private val retrofitClient = RetrofitClient()
    override val weatherRepository: WeatherRepository = WeatherRepositoryImpl(weatherApi = retrofitClient.weatherApi)
}