package com.adrian.dekaid.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.local.entity.MovieEntity
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
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    lateinit var pagedList: PagedList<MovieEntity>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(movieRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovieFavorite = pagedList
        `when`(dummyMovieFavorite.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovieFavorite
        `when`(movieRepository.getFavoriteMovie()).thenReturn(movie)
        val favoriteMovieData = viewModel.getFavoriteMovie().value
        verify(movieRepository).getFavoriteMovie()
        assertNotNull(favoriteMovieData)
        assertEquals(5, favoriteMovieData?.size)
        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovieFavorite)
    }
}