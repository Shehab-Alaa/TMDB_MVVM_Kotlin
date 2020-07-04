package com.example.tmdbcleanarchitecture.ui.main.movie_details

import androidx.lifecycle.MutableLiveData
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.model.MovieDetails
import com.example.tmdbcleanarchitecture.data.model.MovieReview
import com.example.tmdbcleanarchitecture.data.model.MovieTrailer
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.ui.base.BaseViewModel

class MovieDetailsViewModel(dataManager: DataManager) : BaseViewModel(dataManager) {

    lateinit var movie : Movie

    val movieDetails : MutableLiveData<MovieDetails> by lazy {
        getDataManager().getApiRepository().fetchLiveMovieDetailsData(movie.id)
    }

    val similarMoviesList : MutableLiveData<List<Movie>> by lazy{
        getDataManager().getApiRepository().fetchLiveSimilarMoviesList(movie.id)
    }

    val movieReviewsList : MutableLiveData<List<MovieReview>> by lazy {
        getDataManager().getApiRepository().fetchLiveMovieReviewsList(movie.id)
    }

    val movieTrailersList : MutableLiveData<List<MovieTrailer>> by lazy {
        getDataManager().getApiRepository().fetchLiveMovieTrailersList(movie.id)
    }

    val isFavoriteMovie : MutableLiveData<Boolean> by lazy {
        // To Be Done Using DataBase
        MutableLiveData<Boolean>(false)
    }

    fun onFavoriteBtnClick() {
        if(isFavoriteMovie.value!!){
            // Remove From Database
        }else{
            // Add To Database
        }
    }
}