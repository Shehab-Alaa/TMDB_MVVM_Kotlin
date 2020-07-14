package com.example.tmdbcleanarchitecture.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.databinding.ActivityMainBinding
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory
import com.example.tmdbcleanarchitecture.ui.base.BaseActivity
import com.example.tmdbcleanarchitecture.utils.AppConstants
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val sharedPreferences : SharedPreferences by inject()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModelsFactory : ViewModelsFactory by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setApplicationTheme()
        super.onCreate(savedInstanceState)
        val toolbar: Toolbar = findViewById(R.id.toolbarX)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = getViewDataBinding().drawerLayout
        val navView: NavigationView = getViewDataBinding().navView
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nowPlayingMoviesItem , R.id.popularMoviesItem , R.id.topRatedMoviesItem ,
            R.id.upcomingMoviesItem , R.id.favoriteMoviesItem , R.id.settingsItem
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setApplicationTheme(){
        when (sharedPreferences.getString(AppConstants.SELECTED_THEME , AppConstants.DEFAULT_THEME)){
            AppConstants.DARK_THEME -> setTheme(R.style.AppTheme)
            AppConstants.LIGHT_THEME -> setTheme(R.style.LightAppTheme)
            else -> {
                // Hit Shared Preference For First Time (set dark theme as default)
                setTheme(R.style.AppTheme)
                with(receiver = sharedPreferences.edit()){
                    putString(AppConstants.SELECTED_THEME , AppConstants.DARK_THEME)
                    apply()
                }
            }
        }
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }

    override fun initViewModel(): MainViewModel {
        return viewModelsFactory.create(AppConstants.VIEW_MODEL_KEY , MainViewModel::class.java , SavedStateHandle(mapOf()))
    }
}