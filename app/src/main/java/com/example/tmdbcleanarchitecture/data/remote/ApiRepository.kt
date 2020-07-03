package com.example.tmdbcleanarchitecture.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.ui.main.movie.MovieDataSourceFactory

class ApiRepository{

    private lateinit var moviePagedList: LiveData<PagedList<Movie>>
    private lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviesPagedList (category: String) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(category)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ApiClient.MOVIE_PER_PAGE)
            .build()
        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
        return moviePagedList
    }
}