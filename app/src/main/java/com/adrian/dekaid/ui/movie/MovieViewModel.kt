package com.adrian.dekaid.ui.movie

import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovie(sort: String) = movieRepository.loadAllMovies(sort)
}