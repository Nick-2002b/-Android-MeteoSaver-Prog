package com.unibo.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibo.domain.model.Weather
import com.unibo.domain.usecases.FetchRemoteWeatherUseCase
import com.unibo.domain.usecases.GetWeatherListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel (
    private val getWeatherListUseCase: GetWeatherListUseCase,
    private val fetchRemoteWeatherUseCase: FetchRemoteWeatherUseCase
): ViewModel() {

    private val _weatherList = MutableStateFlow<List<Weather>>(listOf())
    val weatherList: StateFlow<List<Weather>> = _weatherList

    private val _showLoader = MutableStateFlow(false)
    val showLoader: StateFlow<Boolean> = _showLoader

    init {
        observeWeatherList()
    }

    fun searchCity(cityName: String){
        fetchRemoteWeatherUseCase.invoke(cityName)
    }

    private fun observeWeatherList(){
        viewModelScope.launch {
            _showLoader.value = true

            getWeatherListUseCase().collect { weatherFromRepo ->
                _weatherList.value = weatherFromRepo
                _showLoader.value = false
            }
        }
    }

}