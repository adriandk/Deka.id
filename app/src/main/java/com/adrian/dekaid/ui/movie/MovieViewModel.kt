package com.adrian.dekaid.ui.movie

import com.adrian.dekaid.data.MovieData
import com.adrian.dekaid.utils.DataDummy

class MovieViewModel {
    fun getMovie(): List<MovieData> = DataDummy.getMovieData()
}