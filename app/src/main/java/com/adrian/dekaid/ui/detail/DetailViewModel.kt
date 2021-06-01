package com.adrian.dekaid.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.model.MovieData

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    companion object {
        const val TV_SHOW = "tv show"
        const val MOVIE = "movie"
    }

    private lateinit var movies: LiveData<MovieData>

    fun getMovie(movieId: Int, movieType: String) {
        when (movieType) {
            MOVIE -> {
                movies = movieRepository.loadDetailMovies(movieId)
            }
            TV_SHOW -> {
                movies = movieRepository.loadDetailShow(movieId)
            }
        }
    }

    fun getMovieDetail() = movies

}