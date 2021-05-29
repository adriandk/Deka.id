package com.adrian.dekaid.data.source.remote.network

import com.adrian.dekaid.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getAllMovies(
        @Query("api_key") api: String
    ): Call<ListMovieResponse>

    @GET("tv/popular")
    fun getAllShow(
        @Query("api_key") api: String
    ): Call<ListMovieResponse>
}