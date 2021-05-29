package com.adrian.dekaid.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adrian.dekaid.BuildConfig
import com.adrian.dekaid.data.source.MovieDataSource
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.data.source.remote.network.ApiConfig
import com.adrian.dekaid.data.source.remote.response.ListMovieResponse
import com.adrian.dekaid.utils.DataMapper
import com.adrian.dekaid.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository : MovieDataSource {

    private val listMovie = MutableLiveData<List<MovieData>>()
    private val listShow = MutableLiveData<List<MovieData>>()

    companion object {
        const val TAG = "MOVIE_REPOSITORY"
        private const val TIME_IN_MILLIS: Long = 1500
        internal const val API_KEY = BuildConfig.API_KEY

        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository = instance ?: synchronized(this) {
            instance ?: MovieRepository()
        }
    }

    override fun loadAllMovies(): LiveData<List<MovieData>> {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            delay(TIME_IN_MILLIS)
            ApiConfig.getApiService().getAllMovies(API_KEY)
                .enqueue(object : Callback<ListMovieResponse> {
                    override fun onResponse(
                        call: Call<ListMovieResponse>,
                        response: Response<ListMovieResponse>
                    ) {
                        val listMapMovie =
                            response.body()?.movies?.let { DataMapper.movieMapFromEntityList(it) }
                        listMovie.postValue(listMapMovie)
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                        Log.e(TAG, t.stackTraceToString())
                    }
                })
        }
        return listMovie
    }

    override fun loadAllShow(): LiveData<List<MovieData>> {
        EspressoIdlingResource.increment()
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
                        listShow.postValue(listMapShow)
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                        Log.e(TAG, t.stackTraceToString())
                    }
                })
        }
        return listShow
    }
}