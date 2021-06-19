package com.adrian.dekaid.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adrian.dekaid.BuildConfig.API_KEY
import com.adrian.dekaid.data.source.remote.network.ApiConfig
import com.adrian.dekaid.data.source.remote.response.ListMovieResponse
import com.adrian.dekaid.data.source.remote.response.MoviesResponse
import com.adrian.dekaid.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getAllMovie(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        val movieList = MutableLiveData<ApiResponse<List<MoviesResponse>>>()

        ApiConfig.getApiService().getAllMovies(API_KEY)
            .enqueue(object : Callback<ListMovieResponse> {
                override fun onResponse(
                    call: Call<ListMovieResponse>,
                    response: Response<ListMovieResponse>
                ) {
                    movieList.value =
                        ApiResponse.Success(response.body()?.movies as List<MoviesResponse>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                    Log.e("Remote Data", "Failed ${t.message}")
                    EspressoIdlingResource.decrement()
                }
            })
        return movieList
    }

    fun getAllShow(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        val showList = MutableLiveData<ApiResponse<List<MoviesResponse>>>()

        ApiConfig.getApiService().getAllShow(API_KEY).enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                showList.value = ApiResponse.Success(response.body()?.movies as List<MoviesResponse>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                Log.e("Remote Data", "Failed ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
        return showList
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<MoviesResponse>> {
        EspressoIdlingResource.increment()
        val detailMovie = MutableLiveData<ApiResponse<MoviesResponse>>()

        ApiConfig.getApiService().getDetailMovie(movieId, API_KEY)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    detailMovie.value = ApiResponse.Success(response.body() as MoviesResponse)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.e("Remote Data", "Failed ${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })

        return detailMovie
    }

    fun getDetailShow(showId: Int): LiveData<ApiResponse<MoviesResponse>> {
        EspressoIdlingResource.increment()
        val detailShow = MutableLiveData<ApiResponse<MoviesResponse>>()

        ApiConfig.getApiService().getDetailShow(showId, API_KEY)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    detailShow.value = ApiResponse.Success(response.body() as MoviesResponse)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.e("Remote Data", "Failed ${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })

        return detailShow
    }

}