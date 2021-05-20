package com.adrian.dekaid.ui.detail

import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieData
import com.adrian.dekaid.utils.DataDummy

class DetailViewModel : ViewModel() {

    companion object {
        const val TV_SHOW = "tv show"
        const val MOVIE = "movie"
    }

    private lateinit var movies: MovieData

    fun getMovie(movieId: String, movieType: String) {
        when (movieType) {
            TV_SHOW -> {
                for (show in DataDummy.getShowData()) {
                    if (show.movieId == movieId) {
                        movies = show
                    }
                }
            }
            MOVIE -> {
                for (movie in DataDummy.getMovieData()) {
                    if (movie.movieId == movieId) {
                        movies = movie
                    }
                }
            }
        }
    }

    fun getMovieDetail() = movies

}