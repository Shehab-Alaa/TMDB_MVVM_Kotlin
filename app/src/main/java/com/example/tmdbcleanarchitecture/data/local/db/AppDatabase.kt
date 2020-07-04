package com.example.tmdbcleanarchitecture.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbcleanarchitecture.data.local.db.dao.MoviesDAO
import com.example.tmdbcleanarchitecture.data.model.db.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val moviesDAO: MoviesDAO
}