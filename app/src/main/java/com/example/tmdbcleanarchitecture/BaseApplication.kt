package com.example.tmdbcleanarchitecture

import android.app.Application
import com.example.tmdbcleanarchitecture.di.module.appModule
import com.example.tmdbcleanarchitecture.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(appModule , viewModelModule))
        }
    }
}