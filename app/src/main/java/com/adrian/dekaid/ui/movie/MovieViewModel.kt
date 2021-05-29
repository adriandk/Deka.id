package com.adrian.dekaid.ui.movie

import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovie(): List<MovieData> = DataDummy.getMovieData()
}