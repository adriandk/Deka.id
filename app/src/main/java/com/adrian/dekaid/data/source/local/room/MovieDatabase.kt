package com.adrian.dekaid.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity

@Database(entities = [MovieEntity::class, ShowEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase()