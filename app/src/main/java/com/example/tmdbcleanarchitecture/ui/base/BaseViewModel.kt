package com.example.tmdbcleanarchitecture.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.tmdbcleanarchitecture.data.DataManager

abstract class BaseViewModel(private val dataManager: DataManager) : ViewModel()  {

    val isLoading = ObservableBoolean()

    fun getDataManager() : DataManager = dataManager

    fun setIsLoading(b:Boolean){
        isLoading.set(b)
    }
}