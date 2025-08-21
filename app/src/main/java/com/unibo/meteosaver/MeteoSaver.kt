package com.unibo.meteosaver
import android.app.Application

class MeteoSaver: Application() {

    override fun onCreate() {
        super.onCreate()

//        UseCaseProvider.setup(
//            repositoryProvider = RepositoryProviderImpl(context = this.applicationContext)
//        )
    }
}