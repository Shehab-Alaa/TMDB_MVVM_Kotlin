package com.example.tmdbcleanarchitecture.di.module

import androidx.core.os.bundleOf
import androidx.savedstate.SavedStateRegistryOwner
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory
import org.koin.dsl.module

val viewModelModule = module {
    single { (owner : SavedStateRegistryOwner) -> provideViewModelFactory(owner)}
}

private fun provideViewModelFactory(owner: SavedStateRegistryOwner) = ViewModelsFactory(owner ,
    bundleOf()
)