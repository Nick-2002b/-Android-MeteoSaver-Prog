package com.unibo.ui.homepage

import androidx.lifecycle.ViewModel
import com.unibo.domain.usecases.FetchRemoteWeatherUseCase
import com.unibo.domain.usecases.GetWeatherListUseCase

class WeatherViewModel (
    private val getWeatherListUseCase: GetWeatherListUseCase,
    private val fetchRemoteWeatherUseCase: FetchRemoteWeatherUseCase
): ViewModel() {

}