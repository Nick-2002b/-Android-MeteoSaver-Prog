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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unibo.domain.model.Weather
import com.unibo.ui.common.MeteoSaverAppBar
import com.unibo.ui.common.PrimaryBtn
import com.unibo.ui.common.WeatherCard
import com.unibo.ui.compose.common.Loader
import com.unibo.ui.R



@Composable
fun HomeScreen(
    viewModel: WeatherViewModel,
    onItemClick: (Weather) -> Unit,
){
    val weatherList = viewModel.weatherList.collectAsStateWithLifecycle()
    val isLoading = viewModel.showLoader.collectAsStateWithLifecycle()

    var showDialog by remember { mutableStateOf(false) }
    var cityNameInput by remember { mutableStateOf("") }

    HomeScreenLayout(
        weatherList = weatherList.value,
        isLoading = isLoading.value,
        onItemClick = onItemClick,
        onRefresh = { viewModel.onRefresh()},
        onAddCityClick = { showDialog = true }
    )
    if(showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                cityNameInput = ""
            },
            title = { Text(text = stringResource(id = R.string.add_new_city))},
            text = {
                OutlinedTextField(
                    value = cityNameInput,
                    onValueChange = { cityNameInput = it },
                    label = { Text(stringResource(id = R.string.dialog_city_name)) },
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (cityNameInput.isNotBlank()){
                            viewModel.searchCity(cityNameInput)
                        }
                        showDialog = false
                        cityNameInput = ""
                    }
                ) {
                    Text(stringResource(id = R.string.add_btn))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        cityNameInput = ""
                    }
                ) {
                    Text(stringResource(R.string.cancel_btn))
                }
            },
        )
    }
}

// Function to create the HomeScreen layout
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenLayout(
    weatherList: List<Weather>,
    isLoading: Boolean,
    onItemClick: (Weather) -> Unit,
    onRefresh: () -> Unit,
    onAddCityClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        MeteoSaverAppBar(
            title = stringResource(id = R.string.app_name),
            actions = {
                IconButton(onClick = onRefresh) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(id = R.string.update_weather_data),
                    )
                }
            }
        )
        PrimaryBtn(
            stringResource(id = R.string.add_city_btn),
            onBtnClick = onAddCityClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.spacing_medium)),
        )
        if (isLoading && weatherList.isEmpty()) {
            Loader()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.spacing_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.weatherCard_spacing))

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
            feelsLike = 29.0,
            tempMin = 20.0,
            tempMax = 30.0
        ),
        Weather(
            cityName = "Roma",
            temperature = 19.0,
            weatherDescription = "Pioggia",
            icon = "",
            humidity = 80.0,
            windSpeed = 20.0,
            feelsLike = 18.0,
            tempMin = 15.0,
            tempMax = 25.0
        ),
        Weather(
            cityName = "Torino",
            temperature = 29.0,
            weatherDescription = "Pioggia",
            icon = "",
            humidity = 80.0,
            windSpeed = 20.0,
            feelsLike = 18.0,
            tempMin = 15.0,
            tempMax = 25.0
        )
    )

    HomeScreenLayout(
        weatherList = fakeWeatherList,
        isLoading = false,
        onItemClick = {},
        onRefresh = {},
        onAddCityClick = {}
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
