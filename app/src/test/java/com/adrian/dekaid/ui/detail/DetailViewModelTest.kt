package com.adrian.dekaid.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.ui.detail.DetailViewModel.Companion.MOVIE
import com.adrian.dekaid.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.adrian.dekaid.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    private val showId = dummyShow.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<MovieData>

    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDetail = MutableLiveData<MovieData>()
        movieDetail.value = dummyMovie

        `when`(movieRepository.loadDetailMovies(movieId)).thenReturn(movieDetail)
        viewModel.getMovie(movieId, MOVIE)
        val detailData = viewModel.getMovieDetail().value as MovieData
        verify(movieRepository).loadDetailMovies(movieId)

        assertNotNull(detailData)
        assertEquals(dummyMovie.movieId, detailData.movieId)
        assertEquals(dummyMovie.movieTitle, detailData.movieTitle)
        assertEquals(dummyMovie.movieDuration, detailData.movieDuration)
        assertEquals(dummyMovie.movieVote.toString(), detailData.movieVote.toString())
        assertEquals(dummyMovie.movieDescription, detailData.movieDescription)
        assertEquals(dummyMovie.movieReleaseYear, detailData.movieReleaseYear)
        assertEquals(dummyMovie.movieImage, detailData.movieImage)
        assertEquals(dummyMovie.posterLink, detailData.posterLink)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Before
    fun setUpShow() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getShowDetail() {
        val showDetail = MutableLiveData<MovieData>()
        showDetail.value = dummyShow

        `when`(movieRepository.loadDetailShow(showId)).thenReturn(showDetail)
        viewModel.getMovie(showId, TV_SHOW)
        val detailData = viewModel.getMovieDetail().value as MovieData
        verify(movieRepository).loadDetailShow(showId)

        assertNotNull(detailData)
        assertEquals(dummyShow.movieId, detailData.movieId)
        assertEquals(dummyShow.movieName, detailData.movieName)
        assertEquals(dummyShow.showSeason, detailData.showSeason)
        assertEquals(dummyShow.movieVote.toString(), detailData.movieVote.toString())
        assertEquals(dummyShow.movieDescription, detailData.movieDescription)
        assertEquals(dummyShow.movieFirstAir, detailData.movieFirstAir)
        assertEquals(dummyShow.movieImage, detailData.movieImage)
        assertEquals(dummyShow.posterLink, detailData.posterLink)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyShow)
    }
}