package com.example.tmdbcleanarchitecture.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.ui.MainViewModel
import com.example.tmdbcleanarchitecture.ui.main.movie.MoviesViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelsFactory(private val dataManager: DataManager) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(dataManager) as T
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(dataManager) as T
            //modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> FavoriteMoviesViewModel(dataManager) as T
            //modelClass.isAssignableFrom(MovieDetailsViewModel::class.java) -> MovieDetailsViewModel(dataManager) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}