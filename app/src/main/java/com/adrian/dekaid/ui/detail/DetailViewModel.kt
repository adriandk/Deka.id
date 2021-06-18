package com.adrian.dekaid.ui.detail

import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun setFavoriteMovie(movie: MovieEntity, newStatus: Boolean) {
        movieRepository.setFavoriteMovie(movie, newStatus)
    }

    fun setFavoriteShow(show: ShowEntity, newStatus: Boolean) {
        movieRepository.setFavoriteShow(show, newStatus)
    }
}