package com.adrian.dekaid.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.utils.DataDummy
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieData>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val dummyData = DataDummy.getMovieData()
        val movies = MutableLiveData<List<MovieData>>()
        movies.value = dummyData

        `when`(movieRepository.loadAllMovies()).thenReturn(movies)
        val movieData = viewModel.getMovie().value
        verify(movieRepository).loadAllMovies()
        assertNotNull(movieData)
        assertThat(movieData?.size).isEqualTo(movies.value!!.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(movieData)
    }
}