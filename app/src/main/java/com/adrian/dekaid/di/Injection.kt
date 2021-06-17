package com.adrian.dekaid.di

import android.content.Context
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.local.LocalDataSource
import com.adrian.dekaid.data.source.local.room.MovieDatabase
import com.adrian.dekaid.data.source.remote.RemoteDataSource
import com.adrian.dekaid.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}