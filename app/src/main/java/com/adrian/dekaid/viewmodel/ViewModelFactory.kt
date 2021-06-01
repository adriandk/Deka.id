package com.adrian.dekaid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.di.Injection
import com.adrian.dekaid.ui.detail.DetailViewModel
import com.adrian.dekaid.ui.movie.MovieViewModel
import com.adrian.dekaid.ui.tvshow.ShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository())
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(ShowViewModel::class.java) -> {
                return ShowViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(movieRepository) as T
            }
            else -> {
                throw Throwable("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

}