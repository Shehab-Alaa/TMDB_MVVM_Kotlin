package com.example.tmdbcleanarchitecture.ui.main.movie

import android.util.Log
import android.widget.Toast
import androidx.paging.PageKeyedDataSource
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.data.remote.network.ApiService
import com.example.tmdbcleanarchitecture.utils.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDataSource(val category: String) : PageKeyedDataSource<Int, Movie>() , KoinComponent
{

    private val apiService : ApiService by inject()
    private val page = ApiClient.FIRST_PAGE

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        GlobalScope.launch {
            val moviesList =  apiService.getMovies(category ,
                ApiClient.API_KEY , ApiClient.LANGUAGE ,
                page).movieList
            callback.onResult(moviesList , null , page + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        GlobalScope.launch {
           val dataResponse = apiService.getMovies(category, ApiClient.API_KEY , ApiClient.LANGUAGE ,
                params.key)
           if (dataResponse.totalPages >= params.key){
              callback.onResult(dataResponse.movieList , params.key + 1)
           }else{
               Log.i("Here" , "End of Pages")
           }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

}