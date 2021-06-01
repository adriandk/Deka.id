package com.adrian.dekaid.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.model.MovieData

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieData>> = movieRepository.loadAllMovies()
}