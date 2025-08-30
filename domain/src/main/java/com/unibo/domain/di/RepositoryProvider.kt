package com.unibo.domain.di

import com.unibo.domain.repository.WeatherRepository

interface RepositoryProvider {
    val weatherRepository: WeatherRepository
}