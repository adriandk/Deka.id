package com.adrian.dekaid.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.Resource
import com.adrian.dekaid.ui.detail.DetailActivity.Companion.MOVIE
import com.adrian.dekaid.ui.detail.DetailActivity.Companion.SHOW

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>
    private lateinit var detailTvShow: LiveData<Resource<ShowEntity>>

    fun getDetail(id: Int, category: String) {
        when(category) {
            MOVIE -> {
                detailMovie = movieRepository.getDetailMovie(id)
            }
            SHOW -> {
                detailTvShow = movieRepository.getDetailShow(id)
            }
        }
    }

    fun setFavoriteMovie(movie: MovieEntity, newStatus: Boolean) {
        movieRepository.setFavoriteMovie(movie, newStatus)
    }

    fun setFavoriteShow(show: ShowEntity, newStatus: Boolean) {
        movieRepository.setFavoriteShow(show, newStatus)
    }

    fun detailDataMovie() = detailMovie

    fun detailDataShow() = detailTvShow
}