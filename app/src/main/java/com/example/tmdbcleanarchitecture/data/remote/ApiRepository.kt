package com.example.tmdbcleanarchitecture.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.tmdbcleanarchitecture.data.model.MovieDetails
import com.example.tmdbcleanarchitecture.data.model.MovieReview
import com.example.tmdbcleanarchitecture.data.model.MovieTrailer
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.data.remote.network.ApiService
import com.example.tmdbcleanarchitecture.data.remote.network.YoutubeClient
import com.example.tmdbcleanarchitecture.ui.main.movie.MovieDataSourceFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ApiRepository : KoinComponent{

    private val apiService: ApiService by inject()
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

    fun fetchLiveMovieDetailsData(movieID:Int) : MutableLiveData<MovieDetails>{
        val movieDetails : MutableLiveData<MovieDetails> = MutableLiveData()
        GlobalScope.launch {
            movieDetails.postValue(apiService.getMovieDetails(movieID , ApiClient.API_KEY , ApiClient.LANGUAGE))
        }
        return movieDetails
    }

    fun fetchLiveSimilarMoviesList(movieID: Int) : MutableLiveData<List<Movie>>{
        val similarMoviesList : MutableLiveData<List<Movie>> = MutableLiveData()
        GlobalScope.launch {
            similarMoviesList.postValue(apiService.getSimilarMovies(movieID , ApiClient.API_KEY , ApiClient.LANGUAGE , ApiClient.FIRST_PAGE).movieList)
        }
        return similarMoviesList
    }

    fun fetchLiveMovieReviewsList(movieID: Int) : MutableLiveData<List<MovieReview>>{
        val movieReviewsList : MutableLiveData<List<MovieReview>> = MutableLiveData()
        GlobalScope.launch {
            movieReviewsList.postValue(apiService.getMovieReviews(movieID,ApiClient.API_KEY , ApiClient.LANGUAGE , ApiClient.FIRST_PAGE).movieReviews)
        }
        return movieReviewsList
    }

    fun fetchLiveMovieTrailersList(movieID: Int) : MutableLiveData<List<MovieTrailer>>{
        val movieTrailersList : MutableLiveData<List<MovieTrailer>> = MutableLiveData()
        GlobalScope.launch {
            movieTrailersList.postValue(apiService.getMovieTrailers(movieID,ApiClient.API_KEY, ApiClient.LANGUAGE).movieTrailers)
        }
        return movieTrailersList
    }
}