package com.adrian.dekaid.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.di.Injection
import com.adrian.dekaid.ui.detail.DetailViewModel
import com.adrian.dekaid.ui.favorite.movie.FavoriteMovieViewModel
import com.adrian.dekaid.ui.favorite.show.FavoriteShowViewModel
import com.adrian.dekaid.ui.movie.MovieViewModel
import com.adrian.dekaid.ui.tvshow.ShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(ShowViewModel::class.java) -> {
                ShowViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteShowViewModel::class.java) -> {
                FavoriteShowViewModel(movieRepository) as T
            }
            else -> {
                throw Throwable("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

}