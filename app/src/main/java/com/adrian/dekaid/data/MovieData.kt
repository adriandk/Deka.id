package com.adrian.dekaid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData(
    val movieId: String,
    val movieTitle: String,
    val movieDuration: String,
    val movieGenre: String,
    val movieDescription: String,
    val movieReleaseYear: String,
    val movieImage: Int,
) : Parcelable