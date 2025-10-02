package com.unibo.ui.screens.details

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.unibo.domain.model.Weather
import com.unibo.ui.common.MeteoSaverAppBar
import com.unibo.ui.common.PrimaryBtn
import com.unibo.ui.screens.homepage.WeatherViewModel
import com.unibo.ui.R
import com.unibo.ui.compose.common.Loader

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
        Loader()
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
                        contentDescription = stringResource(id = R.string.go_back_btn),
                    )
                }
            }
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.description_icon_size))
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = weatherDesc.icon,
                        contentDescription = stringResource(id = R.string.weather_icon_desc),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.description_icon_size))
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "%.1f°C".format(weatherDesc.temperature),
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
                val detailTextStyle = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = integerResource(id = R.integer.detail_text_font_size).sp
                )
                Text(
                    text = stringResource(id = R.string.humidity, weatherDesc.humidity),
                    style = detailTextStyle,
                )
                Text(
                    text = stringResource(id = R.string.wind, weatherDesc.windSpeed),
                    style = detailTextStyle,
                )
                Text(
                    text = stringResource(id = R.string.temp, weatherDesc.tempMin, weatherDesc.tempMax),
                    style = detailTextStyle,
                )
                Text(
                    text = stringResource(id = R.string.feels_like, weatherDesc.feelsLike),
                    style = detailTextStyle,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            PrimaryBtn(
                btnName = stringResource(R.string.delete_City),
                onBtnClick = onDeleteClick,
                backgroundColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Preview (showBackground = true)
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
