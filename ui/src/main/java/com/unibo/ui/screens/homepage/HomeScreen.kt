package com.unibo.ui.screens.homepage

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unibo.domain.model.Weather
import com.unibo.ui.common.MeteoSaverAppBar
import com.unibo.ui.common.PrimaryBtn
import com.unibo.ui.compose.common.Loader



@Composable
fun HomeScreen(
    viewModel: WeatherViewModel,
    onItemClick: (Weather) -> Unit,
){
    val weatherList = viewModel.weatherList.collectAsStateWithLifecycle()
    val isLoading = viewModel.showLoader.collectAsStateWithLifecycle()
    HomeScreenLayout(
        weatherList = weatherList.value,
        isLoading = isLoading.value,
        onItemClick = onItemClick,
        onRefresh = { viewModel.onRefresh()}
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.searchCity(cityName = "Milano")
    }
}

// Function to create the HomeScreen layout
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenLayout(
    weatherList: List<Weather>,
    isLoading: Boolean,
    onItemClick: (Weather) -> Unit,
    onRefresh: () -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()

    Column(modifier = Modifier.fillMaxSize()) {

        MeteoSaverAppBar(
            title = "MeteoSaver",
            actions = {
                IconButton(onClick = onRefresh) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Aggiorna dati meteo",
                    )
                }
            }
        )
        PrimaryBtn(
            "Aggiungi Città",
            onBtnClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        if (isLoading && weatherList.isEmpty()) {
            Loader()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            items(weatherList) { weather ->
                WeatherCard(
                    weather = weather,
                    onItemClick = onItemClick,
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenPreview() {
    val fakeWeatherList = listOf(
        Weather(
            cityName = "Milano",
            temperature = 27.0,
            weatherDescription = "Sereno",
            icon = "",
            humidity = 55.0,
            windSpeed = 12.0,
            feelsLike = 29.0
        ),
        Weather(
            cityName = "Roma",
            temperature = 19.0,
            weatherDescription = "Pioggia",
            icon = "",
            humidity = 80.0,
            windSpeed = 20.0,
            feelsLike = 18.0
        ),
        Weather(
            cityName = "Torino",
            temperature = 29.0,
            weatherDescription = "Pioggia",
            icon = "",
            humidity = 80.0,
            windSpeed = 20.0,
            feelsLike = 18.0
        )
    )

    HomeScreenLayout(
            weatherList = fakeWeatherList,
            isLoading = false,
            onItemClick = {},
            onRefresh = {}
        )
}

/*
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenLoadingPreview() {
        HomeScreenLayout(
            weatherList = emptyList(),
            isLoading = true,
            onItemClick = {},
            onRefresh = {},
        )
}*/
