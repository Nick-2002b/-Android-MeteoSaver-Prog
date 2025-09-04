package com.unibo.domain.usecases

import com.unibo.domain.repository.WeatherRepository

interface RefreshAllCitiesUseCase: () -> Unit

class RefreshAllCitiesUseCaseImpl(
    private val repository: WeatherRepository
): RefreshAllCitiesUseCase {
    override fun invoke() {
        repository.refreshAllCities()
    }
}