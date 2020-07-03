package com.example.tmdbcleanarchitecture.di.module

import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.data.remote.network.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single { get<Retrofit>().create(ApiService::class.java) }
    single { provideRetrofit() }
}

private fun provideRetrofit() = Retrofit.Builder()
    .baseUrl(ApiClient.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()