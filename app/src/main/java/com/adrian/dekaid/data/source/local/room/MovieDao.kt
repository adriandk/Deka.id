package com.adrian.dekaid.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity

@Dao
interface MovieDao {
    //    Movie
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovie(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

//    Show

    @RawQuery(observedEntities = [ShowEntity::class])
    fun getShow(query: SimpleSQLiteQuery): DataSource.Factory<Int, ShowEntity>

    @Query("SELECT * FROM show WHERE isFavorite = 1")
    fun getFavoriteShow(): DataSource.Factory<Int, ShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<ShowEntity>)

    @Update
    fun updateTvShow(tvShow: ShowEntity)
}