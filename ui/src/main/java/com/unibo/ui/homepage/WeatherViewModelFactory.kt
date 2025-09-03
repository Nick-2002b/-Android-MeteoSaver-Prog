package com.unibo.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unibo.domain.usecases.FetchRemoteWeatherUseCase
import com.unibo.domain.usecases.GetWeatherListUseCase


class WeatherViewModelFactory (
    private val getWeather: GetWeatherListUseCase,
    private val fetchRemoteWeather: FetchRemoteWeatherUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                getWeather,
                fetchRemoteWeather
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}