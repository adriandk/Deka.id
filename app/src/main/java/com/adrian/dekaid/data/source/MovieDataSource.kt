package com.adrian.dekaid.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.Resource

interface MovieDataSource {
    fun loadAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun loadAllShow(sort: String): LiveData<Resource<PagedList<ShowEntity>>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteShow(show: ShowEntity, state: Boolean)

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteShow(): LiveData<PagedList<ShowEntity>>

}