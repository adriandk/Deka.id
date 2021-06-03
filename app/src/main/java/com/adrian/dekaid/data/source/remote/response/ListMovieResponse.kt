package com.adrian.dekaid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("result")
    val movies: List<MoviesResponse>
)