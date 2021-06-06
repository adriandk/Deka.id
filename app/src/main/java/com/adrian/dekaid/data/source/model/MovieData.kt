package com.adrian.dekaid.data.source.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieData(
    val movieId: Int,
    val movieTitle: String,
    val movieVote: Double,
    val movieDescription: String,
    val movieReleaseYear: String,
    val movieImage: String,
    val movieDuration: Int,
    val showSeason: Int,
    val movieFirstAir: String,
    val movieName: String,
    val posterLink: String = "https://image.tmdb.org/t/p/w500$movieImage"
) : Parcelable