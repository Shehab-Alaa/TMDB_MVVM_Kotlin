package com.example.tmdbcleanarchitecture.ui.main.movie

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.data.remote.network.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDataSource(val category: String) : PageKeyedDataSource<Int, Movie>() , KoinComponent
{

    private val apiService : ApiService by inject()
    private val page = ApiClient.FIRST_PAGE

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        Log.i("Here" , "Load Init")
        GlobalScope.launch {
            val moviesList =  apiService.getMovies(category ,
                ApiClient.API_KEY , ApiClient.LANGUAGE ,
                page).movieList
            Log.i("Here" , "Movies " + moviesList.size + " Title " + moviesList[0].title)
            callback.onResult(moviesList , null , page + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        Log.i("Here" , "LoadAfter")
        GlobalScope.launch {
           val moviesList = apiService.getMovies(category, ApiClient.API_KEY , ApiClient.LANGUAGE ,
                params.key).movieList
           callback.onResult(moviesList , params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

}