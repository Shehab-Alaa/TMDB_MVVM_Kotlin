package com.example.tmdbcleanarchitecture.di

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.ui.main.MainViewModel
import com.example.tmdbcleanarchitecture.ui.main.favorite.FavoriteMoviesViewModel
import com.example.tmdbcleanarchitecture.ui.main.movie.MoviesViewModel
import com.example.tmdbcleanarchitecture.ui.main.movie_details.MovieDetailsViewModel
import com.example.tmdbcleanarchitecture.ui.other.setting.SettingsViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject


@Suppress("UNCHECKED_CAST")
class ViewModelsFactory (owner: SavedStateRegistryOwner, defaultArgs: Bundle) : AbstractSavedStateViewModelFactory(owner, defaultArgs) , KoinComponent
{
    private val dataManager : DataManager by inject()

    public override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(dataManager , handle) as T
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(dataManager , handle) as T
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> FavoriteMoviesViewModel(dataManager , handle) as T
            modelClass.isAssignableFrom(MovieDetailsViewModel::class.java) -> MovieDetailsViewModel(dataManager , handle) as T
            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> SettingsViewModel(dataManager , handle) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}