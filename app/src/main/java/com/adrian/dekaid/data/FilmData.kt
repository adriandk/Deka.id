package com.adrian.dekaid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmData(
    val movieId: String,
    val movieImage: Int,
    val movieTitle: String,
    val movieDescription: String,
    val movieReleaseDate: String
) : Parcelable