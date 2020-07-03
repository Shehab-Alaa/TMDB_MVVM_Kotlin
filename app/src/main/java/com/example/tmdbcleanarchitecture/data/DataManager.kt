package com.example.tmdbcleanarchitecture.data

import com.example.tmdbcleanarchitecture.data.local.db.DatabaseRepository
import com.example.tmdbcleanarchitecture.data.remote.ApiRepository

class DataManager {
    private val apiRepository = ApiRepository()
    private val databaseRepository = DatabaseRepository()

    fun getApiRepository() : ApiRepository{
        return apiRepository
    }

    fun getDatabaseRepository() : DatabaseRepository{
        return databaseRepository
    }
}