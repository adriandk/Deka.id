package com.adrian.dekaid.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.di.Injection

class ViewModelFactory private constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository())
        }
    }


}