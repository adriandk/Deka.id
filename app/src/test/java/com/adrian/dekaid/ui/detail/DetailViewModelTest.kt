package com.adrian.dekaid.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.Resource
import com.adrian.dekaid.ui.detail.DetailActivity.Companion.MOVIE
import com.adrian.dekaid.ui.detail.DetailActivity.Companion.SHOW
import com.adrian.dekaid.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.getMovieData()[0]
    private val movieId = dummyMovie.movieId
    private val dummyShow = DataDummy.getShowData()[0]
    private val showId = dummyShow.showId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var showObserver: Observer<Resource<ShowEntity>>

    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.Success(dummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie
        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.getDetail(movieId, MOVIE)
        viewModel.detailDataMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setMovieFavorite() {
        val dummyDetailMovie = Resource.Success(dummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.getDetail(movieId, MOVIE)
        viewModel.setFavoriteMovie((movie.value as Resource.Success<MovieEntity>).data as MovieEntity, true)
        verify(movieRepository).setFavoriteMovie((movie.value as Resource.Success<MovieEntity>).data as MovieEntity, true)
    }

    @Before
    fun setUpShow() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getShowDetail() {
        val dummyDetailShow = Resource.Success(dummyShow)
        val show = MutableLiveData<Resource<ShowEntity>>()
        show.value = dummyDetailShow
        `when`(movieRepository.getDetailShow(showId)).thenReturn(show)
        viewModel.getDetail(showId, SHOW)
        viewModel.detailDataShow().observeForever(showObserver)
        verify(showObserver).onChanged(dummyDetailShow)
    }

    @Test
    fun setShowFavorite() {
        val dummyDetailShow = Resource.Success(dummyShow)
        val show = MutableLiveData<Resource<ShowEntity>>()
        show.value = dummyDetailShow

        `when`(movieRepository.getDetailShow(showId)).thenReturn(show)
        viewModel.getDetail(showId, SHOW)
        viewModel.setFavoriteShow((show.value as Resource.Success<ShowEntity>).data as ShowEntity, true)
        verify(movieRepository).setFavoriteShow((show.value as Resource.Success<ShowEntity>).data as ShowEntity, true)
    }

//
//    @Test
//    fun getShowDetail() {
//        val showDetail = MutableLiveData<MovieData>()
//        showDetail.value = dummyShow
//
//        `when`(movieRepository.loadDetailShow(showId)).thenReturn(showDetail)
//        viewModel.getMovie(showId, TV_SHOW)
//        val detailData = viewModel.getMovieDetail().value as MovieData
//        verify(movieRepository).loadDetailShow(showId)
//
//        assertNotNull(detailData)
//        assertEquals(dummyShow.movieId, detailData.movieId)
//        assertEquals(dummyShow.movieName, detailData.movieName)
//        assertEquals(dummyShow.showSeason, detailData.showSeason)
//        assertEquals(dummyShow.movieVote.toString(), detailData.movieVote.toString())
//        assertEquals(dummyShow.movieDescription, detailData.movieDescription)
//        assertEquals(dummyShow.movieFirstAir, detailData.movieFirstAir)
//        assertEquals(dummyShow.movieImage, detailData.movieImage)
//        assertEquals(dummyShow.posterLink, detailData.posterLink)
//
//        viewModel.getMovieDetail().observeForever(observer)
//        verify(observer).onChanged(dummyShow)
//    }
}