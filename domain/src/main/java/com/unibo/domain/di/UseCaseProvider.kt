package com.unibo.domain.di

import com.unibo.domain.usecases.FetchRemoteWeatherUseCase
import com.unibo.domain.usecases.FetchRemoteWeatherUseCaseImpl
import com.unibo.domain.usecases.GetWeatherListUseCase
import com.unibo.domain.usecases.GetWeatherListUseCaseImpl

object UseCaseProvider {
    lateinit var fetchRemoteWeatherUseCase: FetchRemoteWeatherUseCase
    lateinit var getWeatherListUseCase: GetWeatherListUseCase

    fun setup(
        repositoryProvider: RepositoryProvider,
    ){
        fetchRemoteWeatherUseCase = FetchRemoteWeatherUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )

        getWeatherListUseCase = GetWeatherListUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )

    }
}