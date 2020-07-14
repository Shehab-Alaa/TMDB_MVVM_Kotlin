package com.example.tmdbcleanarchitecture.di.module

import android.app.Application
import android.content.Context
import androidx.core.os.bundleOf
import androidx.room.Room
import androidx.savedstate.SavedStateRegistryOwner
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.local.db.AppDatabase
import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.data.remote.network.ApiService
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory
import com.example.tmdbcleanarchitecture.utils.AppConstants
import com.example.tmdbcleanarchitecture.utils.NetworkUtils
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single { provideDataManager()}

    single { get<Retrofit>().create(ApiService::class.java) }
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get() , get ()) }
    single { provideInterceptor(get()) }
    single { provideCache(get()) }

    single { provideAppDatabase(provideAppContext(get()) , provideDatabaseName())}
    single { provideSharedPreferences(get())}
}

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(ApiClient.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


private fun provideCache(context: Context): Cache {
    val cacheSize : Long = 10 * 1024 * 1024
    return Cache(context.cacheDir, cacheSize)
}

private fun provideOkHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(interceptor)
        .build()
}

private fun provideInterceptor(context: Context): Interceptor {
    return Interceptor { chain ->
        var request: Request = chain.request()
        request = if (!NetworkUtils.isNetworkAvailable(context)) {
            val maxStale = 60 * 60 * 24 * 28 // 4-weeks-stale
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale$maxStale")
                .build()
        } else {
            val maxAge = 5 // fresh data
            request.newBuilder()
                .header("Cache-Control", "public, max-age = $maxAge")
                .build()
        }
        chain.proceed(request)
    }
}

private fun provideAppContext(application: Application) = application

private fun provideDatabaseName() = AppConstants.DATABASE_NAME

private fun provideAppDatabase(context : Context , databaseName : String) = Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
    .allowMainThreadQueries().build()

private fun provideDataManager() = DataManager()

private fun provideSharedPreferences(context: Context) = context.getSharedPreferences(AppConstants.PREF_NAME , Context.MODE_PRIVATE)