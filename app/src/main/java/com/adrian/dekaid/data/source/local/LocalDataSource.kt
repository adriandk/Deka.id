package com.adrian.dekaid.data.source.local

import androidx.paging.DataSource
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.local.room.MovieDao
import com.adrian.dekaid.utils.SortUtils
import com.adrian.dekaid.utils.SortUtils.MOVIE_ENTITIES

class LocalDataSource(private val movieDao: MovieDao) {
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource {
            return instance ?: LocalDataSource(movieDao)
        }
    }

    fun getAllMovie(sort: String): DataSource.Factory<Int, MovieEntity> =
        movieDao.getMovie(SortUtils.getSortedQuery(sort, MOVIE_ENTITIES))

    fun insertMovie(movie: List<MovieEntity>) {
        movieDao.insertMovies(movie)
    }

    fun insertShow(show: List<ShowEntity>) {
        movieDao.insertTvShows(show)
    }

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        movieDao.updateMovie(movie)
    }

    fun setFavoriteShow(show: ShowEntity, state: Boolean) {
        show.isFavorite = state
        movieDao.updateTvShow(show)
    }

}