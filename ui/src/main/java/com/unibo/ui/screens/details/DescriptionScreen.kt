package com.unibo.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.unibo.domain.model.Weather
import com.unibo.ui.common.MeteoSaverAppBar
import com.unibo.ui.common.PrimaryBtn
import com.unibo.ui.screens.homepage.WeatherViewModel

@Composable
fun DescriptionScreen (
    cityName: String,
    descriptionViewModel: WeatherViewModel,
    onBackClick: () -> Unit,
    ){
    LaunchedEffect(key1 = cityName) {
        descriptionViewModel.loadCityDetails(cityName)
    }
    val selectedWeather = descriptionViewModel.selectedWeather.collectAsStateWithLifecycle()
    selectedWeather.value?.let { weatherData ->
        DescriptionScreenLayout(
            weatherDesc = weatherData,
            onBackClick = onBackClick,
            onDeleteClick = {
                descriptionViewModel.deleteCity(weatherData.cityName)
                onBackClick()
            }
        )
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Caricamento descrizione...")
        }
    }
}

@Composable
fun DescriptionScreenLayout(
    weatherDesc: Weather,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
){
    Column (modifier = Modifier.fillMaxSize()) {
        MeteoSaverAppBar(
            title = weatherDesc.cityName,
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Torna indietro",
                    )
                }
            }
        )
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = weatherDesc.icon,
                    modifier = Modifier.size(80.dp),
                    contentDescription = "Icona Meteo",
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "${weatherDesc.temperature}°C",
                        style = MaterialTheme.typography.displayLarge,
                    )
                    Text(
                        text = weatherDesc.weatherDescription,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Gray,
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))

            Column (modifier = Modifier.fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.spacedBy(6.dp)) {
                val detailTextStyle = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp)
                Text(
                    text = "Umidità: ${weatherDesc.humidity}%",
                    style = detailTextStyle,
                )
                Text(
                    text = "Vento: ${weatherDesc.windSpeed} km/h",
                    style = detailTextStyle,
                )
                Text(
                    text = "Min: ${weatherDesc.tempMin}°C Max: ${weatherDesc.tempMax}°C",
                    style = detailTextStyle,
                )
                Text(
                    text = "Percepita: ${weatherDesc.feelsLike}°C",
                    style = detailTextStyle,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            PrimaryBtn(
                btnName = "ELIMINA CITTÀ",
                onBtnClick = onDeleteClick,
                backgroundColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Preview (showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DescriptionScreenPreview() {
    DescriptionScreenLayout (
        weatherDesc = Weather(
            cityName = "Milano",
            temperature = 21.0,
            weatherDescription = "Parzialmente nuvoloso",
            icon = "",
            humidity = 60.0,
            windSpeed = 15.0,
            tempMin = 15.0,
            tempMax = 123.0,
            feelsLike = 15.0
        ),
        onBackClick = {},
        onDeleteClick = {}
    )
}
