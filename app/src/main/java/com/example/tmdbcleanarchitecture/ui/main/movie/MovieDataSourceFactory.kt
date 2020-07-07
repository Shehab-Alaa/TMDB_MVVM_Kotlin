package com.example.tmdbcleanarchitecture.ui.main.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.tmdbcleanarchitecture.data.model.db.Movie

class MovieDataSourceFactory(val category: String) : DataSource.Factory<Int, Movie>()
{
    private val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(category)
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}