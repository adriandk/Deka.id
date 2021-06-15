package com.adrian.dekaid.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity

@Database(entities = [MovieEntity::class, ShowEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MovieDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "Movie.db"
                ).build()
            }
        }
    }
}