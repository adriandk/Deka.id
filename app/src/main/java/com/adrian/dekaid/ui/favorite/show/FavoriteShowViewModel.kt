package com.adrian.dekaid.ui.favorite.show

import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository

class FavoriteShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getFavoriteShow() = movieRepository.getFavoriteShow()

}