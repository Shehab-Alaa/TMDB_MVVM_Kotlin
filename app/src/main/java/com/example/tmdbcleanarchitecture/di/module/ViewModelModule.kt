package com.example.tmdbcleanarchitecture.di.module


import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory
import org.koin.dsl.module

val viewModelModule = module{
    single { provideDataManager()}
    single { ViewModelsFactory(get()) }
}

private fun provideDataManager() = DataManager()