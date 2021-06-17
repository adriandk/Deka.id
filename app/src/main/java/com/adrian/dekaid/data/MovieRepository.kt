package com.adrian.dekaid.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adrian.dekaid.data.source.MovieDataSource
import com.adrian.dekaid.data.source.local.LocalDataSource
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.ApiResponse
import com.adrian.dekaid.data.source.remote.RemoteDataSource
import com.adrian.dekaid.data.source.remote.Resource
import com.adrian.dekaid.data.source.remote.response.MoviesResponse
import com.adrian.dekaid.utils.AppExecutors
import com.adrian.dekaid.utils.DataMapper

@Suppress("DEPRECATION")
class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun loadAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MoviesResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovie(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> {
                return remoteDataSource.getAllMovie()
            }

            override fun saveCallResult(data: List<MoviesResponse>) {
                val movieList = DataMapper.mapFromResponseToEntitiesMovie(data)
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun loadAllShow(sort: String): LiveData<Resource<PagedList<ShowEntity>>> {

        return object :
            NetworkBoundResource<PagedList<ShowEntity>, List<MoviesResponse>>(appExecutors) {

            override fun loadFromDB(): LiveData<PagedList<ShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllShow(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<ShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> {
                return remoteDataSource.getAllShow()
            }

            override fun saveCallResult(data: List<MoviesResponse>) {
                val showList = DataMapper.mapFromResponseToEntitiesShow(data)
                localDataSource.insertShow(showList)
            }

        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun setFavoriteShow(show: ShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteShow(show, state)
        }
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteShow(): LiveData<PagedList<ShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteShow(), config).build()
    }
}