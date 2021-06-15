package com.adrian.dekaid.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity

@Dao
interface MovieDao {

    //    Movie
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    //    TV Show
    @RawQuery(observedEntities = [ShowEntity::class])
    fun getAllShow(query: SupportSQLiteQuery): DataSource.Factory<Int, ShowEntity>

    @Query("SELECT * FROM show where isFavorite = 1")
    fun getFavoriteShow(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: List<ShowEntity>)

    @Update
    fun updateFavoriteShow(Show: ShowEntity)

}