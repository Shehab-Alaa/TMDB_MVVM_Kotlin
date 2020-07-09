package com.example.tmdbcleanarchitecture.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbcleanarchitecture.data.model.db.Movie

interface DbDataSource {
    fun getLiveFavoriteMovies(): LiveData<MutableList<Movie>>
    fun addFavoriteMovie(movie: Movie)
    fun deleteFavoriteMovie(movieID: Int)
    fun checkFavoriteMovie(movieID: Int): MutableLiveData<Boolean>
    fun getRowsCount(): Int
}