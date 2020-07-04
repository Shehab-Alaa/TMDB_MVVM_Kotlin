package com.example.tmdbcleanarchitecture.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.utils.AppConstants
import io.reactivex.Single

@Dao
interface MoviesDAO {
    @Insert
    fun insert(movie: Movie)

    @Query("select * from ${AppConstants.DATABASE_NAME}")
    fun loadAll(): LiveData<MutableList<Movie>>

    @Query("delete from ${AppConstants.DATABASE_NAME} where id = :movieID")
    fun delete(movieID: Int)

    @Query("select COUNT(*) from ${AppConstants.DATABASE_NAME} where id = :movieID")
    fun isExist(movieID: Int): Single<Int>

    @Query("select COUNT(*) from ${AppConstants.DATABASE_NAME}")
    fun countRows(): Int
}