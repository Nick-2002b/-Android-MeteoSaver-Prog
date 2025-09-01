package com.unibo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.unibo.data.local.entities.WeatherLocalModel
import com.unibo.domain.model.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: WeatherLocalModel)

    @Query("SELECT * FROM weather_locations ORDER BY lastUpdate DESC")
    fun getAllWeather(): Flow<List<Weather>>
}