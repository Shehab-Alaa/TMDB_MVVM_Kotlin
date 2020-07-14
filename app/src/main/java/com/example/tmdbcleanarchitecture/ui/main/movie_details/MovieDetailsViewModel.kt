package com.example.tmdbcleanarchitecture.ui.main.movie_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.model.Result
import com.example.tmdbcleanarchitecture.data.model.api.DataResponse
import com.example.tmdbcleanarchitecture.data.model.api.MovieReviewResponse
import com.example.tmdbcleanarchitecture.data.model.api.MovieVideosResponse
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.model.details.MovieDetails
import com.example.tmdbcleanarchitecture.data.model.details.MovieReview
import com.example.tmdbcleanarchitecture.data.model.details.MovieTrailer
import com.example.tmdbcleanarchitecture.ui.base.BaseViewModel
import com.example.tmdbcleanarchitecture.utils.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MovieDetailsViewModel(dataManager: DataManager,saveStateHandle : SavedStateHandle) : BaseViewModel(dataManager,saveStateHandle) {

    var movie : Movie = getSaveStateHandle().get(AppConstants.SELECTED_MOVIE)!!
    private val movieDetails : MutableLiveData<MovieDetails> = MutableLiveData()
    private val similarMoviesList : MutableLiveData<List<Movie>> = MutableLiveData()
    private val movieReviewsList : MutableLiveData<List<MovieReview>> = MutableLiveData()
    private val movieTrailersList : MutableLiveData<List<MovieTrailer>> = MutableLiveData()
    private val coroutineExceptionHandler = CoroutineExceptionHandler{_ , throwable ->
        Log.i("Here" , "Response Handler Issue: " + throwable.localizedMessage)
    }

    fun fetchMovieDetails(){
        viewModelScope.launch(coroutineExceptionHandler) {
            when(val result = getDataManager().getApiRepository().fetchLiveMovieDetailsData(movie.id)){
                is Result.Success<MovieDetails> -> {
                    movieDetails.value = result.data
                }
                is Result.Error ->{
                    Log.i("Here" , "MovieDetails Failed")
                }
            }
        }
    }

    fun fetchSimilarMovies(){
        viewModelScope.launch(coroutineExceptionHandler) {
            when(val result = getDataManager().getApiRepository().fetchLiveSimilarMoviesList(movie.id)){
                is Result.Success<DataResponse> -> {
                    similarMoviesList.value = result.data.movieList
                }
                is Result.Error ->{
                    Log.i("Here" , "SimilarMovies Failed")
                }
            }
        }
    }

    fun fetchMovieReviews(){
        viewModelScope.launch(coroutineExceptionHandler) {
            when(val result = getDataManager().getApiRepository().fetchLiveMovieReviewsList(movie.id)){
                is Result.Success<MovieReviewResponse> -> {
                    movieReviewsList.value = result.data.movieReviews
                }
                is Result.Error ->{
                    Log.i("Here" , "MovieReviews Failed")
                }
            }
        }
    }

    fun fetchMovieTrailers(){
        viewModelScope.launch(coroutineExceptionHandler) {
            when(val result = getDataManager().getApiRepository().fetchLiveMovieTrailersList(movie.id)){
                is Result.Success<MovieVideosResponse> -> {
                    movieTrailersList.value = result.data.movieTrailers
                }
                is Result.Error ->{
                    Log.i("Here" , "MovieTrailers Failed")
                }
            }
        }
    }

    val movieDetailsLiveData : LiveData<MovieDetails> get() = movieDetails
    val similarMoviesLiveData : LiveData<List<Movie>> get() = similarMoviesList
    val movieReviewsLiveData : LiveData<List<MovieReview>> get() = movieReviewsList
    val movieTrailersLiveData : LiveData<List<MovieTrailer>> get() = movieTrailersList

    init {
        fetchMovieDetails()
        fetchSimilarMovies()
        fetchMovieTrailers()
        fetchMovieReviews()
    }

    val isFavoriteMovie : MutableLiveData<Boolean> by lazy {
        getDataManager().getDatabaseRepository().checkFavoriteMovie(movie.id)
    }

    fun onFavoriteBtnClick() {
        if(isFavoriteMovie.value!!){
            getDataManager().getDatabaseRepository().deleteFavoriteMovie(movie.id)
            isFavoriteMovie.postValue(false)
        }else{
            getDataManager().getDatabaseRepository().addFavoriteMovie(movie)
            isFavoriteMovie.postValue(true)
        }
    }
}