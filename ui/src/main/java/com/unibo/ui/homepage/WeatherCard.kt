package com.unibo.ui.homepage
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unibo.domain.model.Weather

@Composable
fun WeatherCard (
    weather: Weather,
    onItemClick: (Weather) -> Unit,
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .clickable { onItemClick(weather) }
        ,shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "Test: ${weather.cityName}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardPreview() {
    WeatherCard(
        weather = Weather(
            cityName = "Milano",
            temperature = 21.0,
            weatherDescription = "Parzialmente nuvoloso",
            icon = "",
            humidity = 60.0,
            windSpeed = 15.0,
            feelsLike = 22.0
        ),
        onItemClick = {}
    )
}