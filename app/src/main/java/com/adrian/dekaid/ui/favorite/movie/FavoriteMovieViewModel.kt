package com.adrian.dekaid.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository

class FavoriteMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getFavoriteMovie() = movieRepository.getFavoriteMovie()
}