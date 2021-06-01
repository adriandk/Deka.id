package com.adrian.dekaid.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.model.MovieData

class ShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getShow(): LiveData<List<MovieData>> = movieRepository.loadAllShow()
}