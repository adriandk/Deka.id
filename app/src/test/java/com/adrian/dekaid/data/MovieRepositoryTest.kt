package com.adrian.dekaid.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.adrian.dekaid.data.source.local.LocalDataSource
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.RemoteDataSource
import com.adrian.dekaid.data.source.remote.Resource
import com.adrian.dekaid.utils.AppExecutors
import com.adrian.dekaid.utils.DataDummy
import com.adrian.dekaid.utils.LiveDataTest
import com.adrian.dekaid.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val dummyMovie = DataDummy.getRemoteMovie()
    private val movieId = dummyMovie[0].id
    private val dummyMovieDetail = DataDummy.getRemoteMovie()[0]

    private val dummyShow = DataDummy.getRemoteShow()
    private val showId = dummyShow[0].id
    private val dummyShowDetail = DataDummy.getRemoteShow()[0]

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun loadAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovie("NEWEST")).thenReturn(dataSourceFactory)
        movieRepository.loadAllMovies("NEWEST")
        val movieEntities = Resource.Success(PagedListUtil.mockPagedList(DataDummy.getMovieData()))
        verify(local).getAllMovie("NEWEST")
        assertNotNull(movieEntities)
        assertEquals(dummyMovie.size, movieEntities.data?.size)
    }

    @Test
    fun loadAllShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ShowEntity>
        `when`(local.getAllShow("NEWEST")).thenReturn(dataSourceFactory)
        movieRepository.loadAllShow("NEWEST")
        val showEntities = Resource.Success(PagedListUtil.mockPagedList(DataDummy.getShowData()))
        verify(local).getAllShow("NEWEST")
        assertNotNull(showEntities)
        assertEquals(dummyMovie.size, showEntities.data?.size)
    }

    @Test
    fun loadDetailMovies() {
        val dataDetail = MutableLiveData<MovieEntity>()
        dataDetail.value = DataDummy.getMovieData()[0]
        `when`(local.getDetailMovie(movieId)).thenReturn(dataDetail)
        val movieEntity = LiveDataTest.getValue(movieRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovieDetail.id, movieEntity.data?.movieId)
    }

    @Test
    fun loadDetailShow() {
        val dataDetail = MutableLiveData<ShowEntity>()
        dataDetail.value = DataDummy.getShowData()[0]
        `when`(local.getDetailShow(showId)).thenReturn(dataDetail)
        val showEntity = LiveDataTest.getValue(movieRepository.getDetailShow(showId))
        verify(local).getDetailShow(showId)
        assertNotNull(showEntity)
        assertEquals(dummyShowDetail.id, showEntity.data?.showId)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovie()
        val movieEntities = Resource.Success(PagedListUtil.mockPagedList(DataDummy.getMovieData()))
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(dummyMovie.size, movieEntities.data?.size)
    }

    @Test
    fun getFavoriteShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ShowEntity>
        `when`(local.getFavoriteShow()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteShow()
        val showEntities = Resource.Success(PagedListUtil.mockPagedList(DataDummy.getShowData()))
        verify(local).getFavoriteShow()
        assertNotNull(showEntities)
        assertEquals(dummyMovie.size, showEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        movieRepository.setFavoriteMovie(DataDummy.getMovieData()[0], true)
        verify(local).setFavoriteMovie(DataDummy.getMovieData()[0], true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun setFavoriteShow() {
        movieRepository.setFavoriteShow(DataDummy.getShowData()[0], true)
        verify(local).setFavoriteShow(DataDummy.getShowData()[0], true)
        verifyNoMoreInteractions(local)
    }

}