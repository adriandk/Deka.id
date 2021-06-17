package com.adrian.dekaid.ui.tvshow

import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository

class ShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getShow(sort: String) = movieRepository.loadAllShow(sort)
}