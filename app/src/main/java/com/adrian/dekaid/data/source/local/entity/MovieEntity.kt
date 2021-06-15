package com.adrian.dekaid.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "movieTitle")
    var movieTitle: String,

    @ColumnInfo(name = "movieVote")
    var movieVote: Double,

    @ColumnInfo(name = "movieDescription")
    var movieDescription: String,

    @ColumnInfo(name = "movieReleaseYear")
    var movieReleaseYear: String,

    @ColumnInfo(name = "movieImage")
    var movieImage: String,

    @ColumnInfo(name = "movieDuration")
    var movieDuration: Int,
)