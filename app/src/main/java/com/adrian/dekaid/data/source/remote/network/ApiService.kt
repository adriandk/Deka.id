package com.adrian.dekaid.data.source.remote.network

import com.adrian.dekaid.data.source.remote.response.ListMovieResponse
import com.adrian.dekaid.data.source.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String
    ): Call<MoviesResponse>

    @GET("tv/{show_id}")
    fun getDetailShow(
        @Path("show_id") id: Int,
        @Query("api_key") api: String
    ): Call<MoviesResponse>
}