package com.adrian.dekaid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releasedDate: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("number_of_seasons")
    val showSeason: Int,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("name")
    val originalName: String
)