package com.adrian.dekaid.di

import com.adrian.dekaid.data.MovieRepository

object Injection {
    fun provideRepository(): MovieRepository {
        return MovieRepository.getInstance()
    }
}