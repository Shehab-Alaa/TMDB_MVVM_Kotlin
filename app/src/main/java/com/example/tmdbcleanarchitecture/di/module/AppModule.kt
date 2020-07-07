package com.example.tmdbcleanarchitecture.di.module

import android.app.Application
import android.content.Context
import androidx.core.os.bundleOf
import androidx.room.Room
import androidx.savedstate.SavedStateRegistry
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.local.db.AppDatabase
import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.data.remote.network.ApiService
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory
import com.example.tmdbcleanarchitecture.utils.AppConstants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single { provideDataManager()}

    single { get<Retrofit>().create(ApiService::class.java) }
    single { provideRetrofit() }

    single { provideAppDatabase(provideAppContext(get()) , provideDatabaseName())}
}

private fun provideRetrofit() = Retrofit.Builder()
    .baseUrl(ApiClient.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideAppContext(application: Application) = application

private fun provideDatabaseName() = AppConstants.DATABASE_NAME

private fun provideAppDatabase(context : Context , databaseName : String) = Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
    .allowMainThreadQueries().build()

private fun provideDataManager() = DataManager()