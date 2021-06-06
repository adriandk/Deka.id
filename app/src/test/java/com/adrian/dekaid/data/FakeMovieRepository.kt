package com.adrian.dekaid.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adrian.dekaid.BuildConfig
import com.adrian.dekaid.data.source.MovieDataSource
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.data.source.remote.network.ApiConfig
import com.adrian.dekaid.data.source.remote.response.ListMovieResponse
import com.adrian.dekaid.data.source.remote.response.MoviesResponse
import com.adrian.dekaid.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeMovieRepository : MovieDataSource {

    private val listMovie = MutableLiveData<List<MovieData>>()
    private val listShow = MutableLiveData<List<MovieData>>()
    private val detailMovie = MutableLiveData<MovieData>()
    private val detailShow = MutableLiveData<MovieData>()

    companion object {
        const val TAG = "MOVIE_REPOSITORY"
        private const val TIME_IN_MILLIS: Long = 2000
        internal const val API_KEY = BuildConfig.API_KEY
    }

    override fun loadAllMovies(): LiveData<List<MovieData>> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(TIME_IN_MILLIS)

            ApiConfig.getApiService().getAllMovies(API_KEY)
                .enqueue(object : Callback<ListMovieResponse> {
                    override fun onResponse(
                        call: Call<ListMovieResponse>,
                        response: Response<ListMovieResponse>
                    ) {
                        val listMapMovie = response.body()?.movies?.let {
                            DataMapper.movieMapFromEntityList(it)
                        }
                        if (listMapMovie != null) {
                            listMovie.postValue(listMapMovie)
                        }
                    }

                    override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                        Log.e(TAG, t.stackTraceToString())
                    }
                })
        }

        return listMovie
    }

    override fun loadAllShow(): LiveData<List<MovieData>> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(TIME_IN_MILLIS)
            ApiConfig.getApiService().getAllShow(API_KEY)
                .enqueue(object : Callback<ListMovieResponse> {
                    override fun onResponse(
                        call: Call<ListMovieResponse>,
                        response: Response<ListMovieResponse>
                    ) {
                        val listMapShow =
                            response.body()?.movies?.let { DataMapper.showMapFromEntityList(it) }

                        if (listMapShow != null) {
                            listShow.postValue(listMapShow)
                        } else {
                            Log.e(TAG, "data null")
                        }
                    }

                    override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                        Log.e(TAG, t.stackTraceToString())
                    }
                })
        }
        return listShow
    }

    override fun loadDetailMovies(movieId: Int): LiveData<MovieData> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(TIME_IN_MILLIS)
            ApiConfig.getApiService().getDetailMovie(movieId, API_KEY)
                .enqueue(object : Callback<MoviesResponse> {
                    override fun onResponse(
                        call: Call<MoviesResponse>,
                        response: Response<MoviesResponse>
                    ) {
                        val mapMovieDetail =
                            response.body()?.let { DataMapper.mapToEntityMovie(it) }
                        if (mapMovieDetail != null) {
                            detailMovie.postValue(mapMovieDetail)
                        }
                    }

                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        Log.e(TAG, t.stackTraceToString())
                    }

                })
        }
        return detailMovie
    }

    override fun loadDetailShow(showId: Int): LiveData<MovieData> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(TIME_IN_MILLIS)
            ApiConfig.getApiService().getDetailShow(showId, API_KEY)
                .enqueue(object : Callback<MoviesResponse> {
                    override fun onResponse(
                        call: Call<MoviesResponse>,
                        response: Response<MoviesResponse>
                    ) {
                        val mapShowDetail = response.body()?.let { DataMapper.mapToEntityShow(it) }
                        if (mapShowDetail != null) {
                            detailShow.postValue(mapShowDetail)
                        }
                    }

                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        Log.e(TAG, t.stackTraceToString())
                    }

                })
        }
        return detailShow
    }

}