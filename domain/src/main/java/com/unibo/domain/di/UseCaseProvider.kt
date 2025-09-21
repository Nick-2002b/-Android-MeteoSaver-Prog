package com.unibo.domain.di

import com.unibo.domain.usecases.DeleteCityUseCase
import com.unibo.domain.usecases.DeleteCityUseCaseImpl
import com.unibo.domain.usecases.FetchRemoteWeatherUseCase
import com.unibo.domain.usecases.FetchRemoteWeatherUseCaseImpl
import com.unibo.domain.usecases.GetWeatherListUseCase
import com.unibo.domain.usecases.GetWeatherListUseCaseImpl
import com.unibo.domain.usecases.RefreshAllCitiesUseCase
import com.unibo.domain.usecases.RefreshAllCitiesUseCaseImpl

object UseCaseProvider {
    lateinit var fetchRemoteWeatherUseCase: FetchRemoteWeatherUseCase
    lateinit var getWeatherListUseCase: GetWeatherListUseCase
    lateinit var refreshAllCitiesUseCase: RefreshAllCitiesUseCase
    lateinit var deleteCityUseCase: DeleteCityUseCase
    fun setup(
        repositoryProvider: RepositoryProvider,
    ){
        fetchRemoteWeatherUseCase = FetchRemoteWeatherUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
        getWeatherListUseCase = GetWeatherListUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
        refreshAllCitiesUseCase = RefreshAllCitiesUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
        deleteCityUseCase = DeleteCityUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )

    }
}