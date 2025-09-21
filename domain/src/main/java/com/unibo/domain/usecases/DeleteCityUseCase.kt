package com.unibo.domain.usecases

import com.unibo.domain.repository.WeatherRepository

interface DeleteCityUseCase {
    suspend fun invoke(cityName: String)
}
class DeleteCityUseCaseImpl(
    private val repository: WeatherRepository
): DeleteCityUseCase  {
    override suspend fun invoke(cityName: String) {
        repository.deleteCity(cityName)
    }
}