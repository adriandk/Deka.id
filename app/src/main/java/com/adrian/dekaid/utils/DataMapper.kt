package com.adrian.dekaid.utils

import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.response.MoviesResponse

object DataMapper {

    fun mapFromResponseToEntitiesMovie(input: List<MoviesResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map { movieData ->
            val movie = MovieEntity(
                movieId = movieData.id,
                movieTitle = movieData.title,
                movieVote = movieData.voteAverage,
                movieReleaseYear = movieData.releasedDate,
                movieImage = movieData.posterPath,
                movieDuration = 0,
                movieDescription = movieData.overview,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapFromResponseToEntitiesShow(input: List<MoviesResponse>): List<ShowEntity> {
        val showList = ArrayList<ShowEntity>()
        input.map { showData ->
            val show = ShowEntity(
                showId = showData.id,
                showName = showData.originalName,
                showDescription = showData.overview,
                showSeason = 0,
                showFirstAir = showData.firstAirDate,
                showImage = showData.posterPath,
                showVote = showData.voteAverage,
                isFavorite = false
            )
            showList.add(show)
        }
        return showList
    }

    fun mapFromResponseToEntityMovie(input: MoviesResponse): MovieEntity {
        return MovieEntity(
            movieId = input.id,
            movieDescription = input.overview,
            movieTitle = input.title,
            movieDuration = input.runtime,
            movieReleaseYear = input.releasedDate,
            movieVote = input.voteAverage,
            movieImage = input.posterPath,
            isFavorite = false
        )
    }

    fun mapFromResponseToEntityShow(input: MoviesResponse): ShowEntity {
        return ShowEntity(
            showId = input.id,
            showName = input.originalName,
            showDescription = input.overview,
            showFirstAir = input.firstAirDate,
            showSeason = input.showSeason,
            showVote = input.voteAverage,
            showImage = input.posterPath,
            isFavorite = false
        )
    }

}