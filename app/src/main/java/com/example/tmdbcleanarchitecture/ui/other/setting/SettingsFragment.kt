package com.example.tmdbcleanarchitecture.ui.other.setting

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.databinding.FragmentSettingsBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseFragment
import com.example.tmdbcleanarchitecture.utils.AppConstants
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {

    private val sharedPreferences : SharedPreferences by inject()
    private val selectedTheme = sharedPreferences.getString(AppConstants.SELECTED_THEME , AppConstants.DARK_THEME)
    private val settingsViewModel : SettingsViewModel by viewModel{ parametersOf( SavedStateHandle(mapOf(AppConstants.SELECTED_THEME to selectedTheme)))}
    private var secondHit = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().darkThemeLiveData.observe(viewLifecycleOwner , Observer {
            if (getViewModel().darkThemeLiveData.value == true && secondHit){
               applySelectedTheme(AppConstants.DARK_THEME)
            }
            secondHit = true
        })

        getViewModel().lightThemeLiveData.observe(viewLifecycleOwner , Observer {
            if (getViewModel().lightThemeLiveData.value == true && secondHit){
               applySelectedTheme(AppConstants.LIGHT_THEME)
            }
            secondHit = true
        })
    }


    private fun applySelectedTheme(selectedTheme : String){
        with(sharedPreferences.edit()){
            putString(AppConstants.SELECTED_THEME , selectedTheme)
            apply()
        }
        activity?.startActivity(Intent(activity, activity!!::class.java))
        activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        activity?.finish()
    }

    override val layoutId: Int
        get() = R.layout.fragment_settings
    override val bindingVariable: Int
        get() = BR.settingsViewModel

    override fun getViewModel(): SettingsViewModel {
        return settingsViewModel
    }

}