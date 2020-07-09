package com.example.tmdbcleanarchitecture.data.local.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class DatabaseRepository : DbDataSource ,  KoinComponent{

    private val appDatabase : AppDatabase by inject()

    override fun getLiveFavoriteMovies() : LiveData<MutableList<Movie>>{
        return appDatabase.moviesDAO.loadAll()
    }

    override fun addFavoriteMovie(movie: Movie){
        appDatabase.moviesDAO.insert(movie)
    }

    override fun deleteFavoriteMovie(movieID : Int){
        appDatabase.moviesDAO.delete(movieID)
    }

    override fun checkFavoriteMovie(movieID: Int) : MutableLiveData<Boolean>{
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

    override fun getRowsCount() : Int{
        return appDatabase.moviesDAO.countRows()
    }
}