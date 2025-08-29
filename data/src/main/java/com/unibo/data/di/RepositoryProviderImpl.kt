package com.unibo.data.di

import com.unibo.data.remote.RetrofitClient
import com.unibo.domain.di.RepositoryProvider

class RepositoryProviderImpl: RepositoryProvider {
    private val retrofitClient = RetrofitClient()
}