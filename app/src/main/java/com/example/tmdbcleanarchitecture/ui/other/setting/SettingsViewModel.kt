package com.example.tmdbcleanarchitecture.ui.other.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.ui.base.BaseViewModel
import com.example.tmdbcleanarchitecture.utils.AppConstants

class SettingsViewModel(dataManager: DataManager, saveStateHandle : SavedStateHandle) : BaseViewModel(dataManager,saveStateHandle) {

    private val selectedTheme:String = getSaveStateHandle().get(AppConstants.SELECTED_THEME)!!
    private val lightTheme : MutableLiveData<Boolean> = MutableLiveData()
    private val darkTheme : MutableLiveData<Boolean> = MutableLiveData()

    init {
        when(selectedTheme){
            AppConstants.DARK_THEME -> darkTheme.postValue(true)
            AppConstants.LIGHT_THEME -> lightTheme.postValue(true)
        }
    }

    fun onLightThemeBtnClick(){
        lightTheme.postValue(true)
    }

    fun onDarkThemeBtnClick(){
        darkTheme.postValue(true)
    }

    val lightThemeLiveData : LiveData<Boolean> get() = lightTheme
    val darkThemeLiveData : LiveData<Boolean> get() = darkTheme
}