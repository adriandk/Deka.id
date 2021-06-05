package com.adrian.dekaid.utils

import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.data.source.remote.response.MoviesResponse

object DataMapper {

    fun mapFromEntityMovie(entity: MoviesResponse): MovieData {
        return MovieData(
            movieId = entity.id,
            movieTitle = entity.title,
            movieDescription = entity.overview,
            movieVote = entity.voteAverage,
            movieImage = entity.posterPath,
            movieReleaseYear = entity.releasedDate,
            movieDuration = entity.runtime,
            showSeason = 0,
            movieName = "",
            movieFirstAir = ""
        )
    }

    fun mapFromEntityShow(entity: MoviesResponse): MovieData {
        return MovieData(
            movieId = entity.id,
            movieTitle = "",
            movieDescription = entity.overview,
            movieVote = entity.voteAverage,
            movieImage = entity.posterPath,
            movieReleaseYear = "",
            movieDuration = 0,
            showSeason = entity.showSeason,
            movieName = entity.originalName,
            movieFirstAir = entity.firstAirDate
        )
    }

    fun mapToEntityMovie(entity: MoviesResponse): MovieData {
        return MovieData(
            movieId = entity.id,
            movieTitle = entity.title,
            movieDescription = entity.overview,
            movieVote = entity.voteAverage,
            movieImage = entity.posterPath,
            movieReleaseYear = entity.releasedDate,
            movieDuration = entity.runtime,
            showSeason = 0,
            movieName = "",
            movieFirstAir = ""
        )
    }

    fun mapToEntityShow(entity: MoviesResponse): MovieData {
        return MovieData(
            movieId = entity.id,
            movieTitle = "",
            movieDescription = entity.overview,
            movieVote = entity.voteAverage,
            movieImage = entity.posterPath,
            movieReleaseYear = "",
            movieDuration = 0,
            showSeason = entity.showSeason,
            movieName = entity.originalName,
            movieFirstAir = entity.firstAirDate
        )
    }

    fun movieMapFromEntityList(entity: ArrayList<MoviesResponse>): ArrayList<MovieData> {
        return entity.map { mapFromEntityMovie(it) } as ArrayList<MovieData>
    }

    fun showMapFromEntityList(entity: ArrayList<MoviesResponse>): ArrayList<MovieData> {
        return entity.map { mapFromEntityShow(it) } as ArrayList<MovieData>
    }
}