package com.unibo.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.unibo.domain.di.UseCaseProvider
import com.unibo.domain.model.Weather
import com.unibo.ui.screens.details.DescriptionScreen
import com.unibo.ui.screens.homepage.HomeScreen
import com.unibo.ui.screens.homepage.WeatherViewModel
import com.unibo.ui.screens.homepage.WeatherViewModelFactory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Composable
fun NavigationHost () {
    val navController = rememberNavController()
    val factory = WeatherViewModelFactory(
        UseCaseProvider.getWeatherListUseCase,
        UseCaseProvider.fetchRemoteWeatherUseCase,
        UseCaseProvider.refreshAllCitiesUseCase
    )
    val weatherViewModel: WeatherViewModel = viewModel(factory = factory)

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                viewModel = weatherViewModel,
                onItemClick = { weather ->
                    val cityNameJsonString = Json.encodeToString(weather)
                    navController.navigate(Description(cityNameJsonString)
                    )
                }
            )
        }
        composable<Description> { navbackStackEntry ->
            val detailsScreen = navbackStackEntry.toRoute<Description>()
            val weather = Json.decodeFromString<Weather>(detailsScreen.weatherJsonString)
            DescriptionScreen(
                cityName = weather.cityName,
                descriptionViewModel = weatherViewModel,
                onBackClick = {navController.popBackStack()}
            )
        }
    }
}

@Serializable
data object Home

@Serializable
data class Description(
    val weatherJsonString: String
)