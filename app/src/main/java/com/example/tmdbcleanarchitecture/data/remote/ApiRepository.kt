package com.example.tmdbcleanarchitecture.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.tmdbcleanarchitecture.data.model.Result
import com.example.tmdbcleanarchitecture.data.model.api.DataResponse
import com.example.tmdbcleanarchitecture.data.model.api.MovieReviewResponse
import com.example.tmdbcleanarchitecture.data.model.api.MovieVideosResponse
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.model.details.MovieDetails
import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.data.remote.network.ApiService
import com.example.tmdbcleanarchitecture.ui.main.movie.MovieDataSourceFactory
import org.koin.core.KoinComponent
import org.koin.core.inject

class ApiRepository : ApiDataSource , KoinComponent{

    private val apiService: ApiService by inject()
    private lateinit var moviePagedList: LiveData<PagedList<Movie>>
    private lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    override fun fetchLiveMoviesPagedList (category: String) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(category)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ApiClient.MOVIE_PER_PAGE)
            .build()
        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
        return moviePagedList
    }


    override suspend fun fetchLiveMovieDetailsData(movieID : Int) : Result<MovieDetails> {
        return try {
            val movieDetails = apiService.getMovieDetails(movieID , ApiClient.API_KEY , ApiClient.LANGUAGE)
            Result.Success(movieDetails)
        }catch (exception : Exception){
            Result.Error(exception.localizedMessage)
        }
    }

    override suspend fun fetchLiveSimilarMoviesList(movieID: Int) : Result<DataResponse>{
        return try {
            val dataResponse = apiService.getSimilarMovies(movieID , ApiClient.API_KEY , ApiClient.LANGUAGE , ApiClient.FIRST_PAGE)
            Result.Success(dataResponse)
        }catch (e : Exception){
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun fetchLiveMovieReviewsList(movieID: Int) : Result<MovieReviewResponse>{
       return try {
           val movieReviewResponse = apiService.getMovieReviews(movieID,ApiClient.API_KEY , ApiClient.LANGUAGE , ApiClient.FIRST_PAGE)
           Result.Success(movieReviewResponse)
       }catch (e : Exception){
           Result.Error(e.localizedMessage)
       }
    }

    override suspend fun fetchLiveMovieTrailersList(movieID: Int) : Result<MovieVideosResponse>{
        return try {
            val movieVideosResponse = apiService.getMovieTrailers(movieID,ApiClient.API_KEY, ApiClient.LANGUAGE)
            Result.Success(movieVideosResponse)
        }catch (e : Exception){
            Result.Error(e.localizedMessage)
        }
    }

}