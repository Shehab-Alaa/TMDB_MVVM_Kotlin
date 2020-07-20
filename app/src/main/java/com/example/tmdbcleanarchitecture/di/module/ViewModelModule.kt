package com.example.tmdbcleanarchitecture.di.module

import androidx.lifecycle.SavedStateHandle
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.ui.main.MainViewModel
import com.example.tmdbcleanarchitecture.ui.main.favorite.FavoriteMoviesViewModel
import com.example.tmdbcleanarchitecture.ui.main.movie.MoviesViewModel
import com.example.tmdbcleanarchitecture.ui.main.movie_details.MovieDetailsViewModel
import com.example.tmdbcleanarchitecture.ui.other.setting.SettingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { provideDataManager()}

    viewModel { (handle : SavedStateHandle) -> MainViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> MoviesViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> FavoriteMoviesViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> MovieDetailsViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> SettingsViewModel(get() , handle) }
}

private fun provideDataManager() = DataManager()
