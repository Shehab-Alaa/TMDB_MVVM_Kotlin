package com.example.tmdbcleanarchitecture.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class DatabaseRepository : KoinComponent{

    private val appDatabase : AppDatabase by inject()

    fun getLiveFavoriteMovies() : LiveData<MutableList<Movie>>{
        return appDatabase.moviesDAO.loadAll()
    }

    fun addFavoriteMovie(movie: Movie){
        appDatabase.moviesDAO.insert(movie)
    }

    fun deleteFavoriteMovie(movieID : Int){
        appDatabase.moviesDAO.delete(movieID)
    }

    fun checkFavoriteMovie(movieID: Int) : MutableLiveData<Boolean>{
        val isFavorite : MutableLiveData<Boolean> = MutableLiveData()
        appDatabase.moviesDAO.isExist(movieID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if (it == 0){
                    isFavorite.postValue(false)
                }else
                    isFavorite.postValue(true)
            },{

            })
        return isFavorite
    }

    fun getRowsCount() : Int{
        return appDatabase.moviesDAO.countRows()
    }
}