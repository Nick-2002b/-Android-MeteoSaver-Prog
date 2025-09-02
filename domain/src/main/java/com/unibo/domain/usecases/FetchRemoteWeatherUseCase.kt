package com.unibo.domain.usecases

import com.unibo.domain.repository.WeatherRepository

interface FetchRemoteWeatherUseCase {
    fun invoke(location: String)
}
class FetchRemoteWeatherUseCaseImpl (
    private val repository: WeatherRepository
): FetchRemoteWeatherUseCase {
    override fun invoke(location: String) {
        repository.fetchRemoteWeatherByCity(location)
    }
}