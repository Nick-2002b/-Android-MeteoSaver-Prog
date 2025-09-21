package com.unibo.ui.screens.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unibo.domain.usecases.DeleteCityUseCase
import com.unibo.domain.usecases.FetchRemoteWeatherUseCase
import com.unibo.domain.usecases.GetWeatherListUseCase
import com.unibo.domain.usecases.RefreshAllCitiesUseCase


class WeatherViewModelFactory (
    private val getWeather: GetWeatherListUseCase,
    private val fetchRemoteWeather: FetchRemoteWeatherUseCase,
    private val refreshAllCitiesUseCase: RefreshAllCitiesUseCase,
    private val deleteCityUseCase: DeleteCityUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                getWeather,
                fetchRemoteWeather,
                refreshAllCitiesUseCase,
                deleteCityUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}