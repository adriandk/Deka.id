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
                movieDuration = movieData.runtime,
                movieDescription = movieData.overview
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
                showSeason = showData.showSeason,
                showFirstAir = showData.firstAirDate,
                showImage = showData.posterPath,
                showVote = showData.voteAverage
            )
            showList.add(show)
        }
        return showList
    }

}