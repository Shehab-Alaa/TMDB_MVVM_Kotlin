package com.example.tmdbcleanarchitecture.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


object NetworkUtils {
    fun isNetworkAvailable(mContext : Context): Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}