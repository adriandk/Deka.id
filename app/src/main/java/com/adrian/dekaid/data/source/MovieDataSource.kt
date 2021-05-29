package com.adrian.dekaid.data.source

import androidx.lifecycle.LiveData
import com.adrian.dekaid.data.source.model.MovieData

interface MovieDataSource {

    fun loadAllMovies(): LiveData<List<MovieData>>

    fun loadAllShow(): LiveData<List<MovieData>>
}