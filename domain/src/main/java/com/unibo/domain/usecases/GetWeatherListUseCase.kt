package com.unibo.domain.usecases

import com.unibo.domain.model.Weather
import com.unibo.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.StateFlow

interface GetWeatherListUseCase: () -> StateFlow<List<Weather>>

class GetWeatherListUseCaseImpl(
    private val repository: WeatherRepository
): GetWeatherListUseCase {
    override fun invoke(): StateFlow<List<Weather>> {
        return repository.weatherList
    }
}
