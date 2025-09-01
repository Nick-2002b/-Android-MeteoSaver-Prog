package com.unibo.meteosaver
import android.app.Application
import com.unibo.data.di.RepositoryProviderImpl
import com.unibo.domain.di.UseCaseProvider

class MeteoSaver: Application() {

    override fun onCreate() {
        super.onCreate()

        UseCaseProvider.setup(
            repositoryProvider = RepositoryProviderImpl(context = this.applicationContext)
        )
    }
}