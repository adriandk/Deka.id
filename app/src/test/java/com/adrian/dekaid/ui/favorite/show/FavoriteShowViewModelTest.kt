package com.adrian.dekaid.ui.favorite.show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.local.entity.ShowEntity
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
class FavoriteShowViewModelTest {

    private lateinit var viewModel: FavoriteShowViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<ShowEntity>>

    @Mock
    lateinit var pagedList: PagedList<ShowEntity>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = FavoriteShowViewModel(movieRepository)
    }

    @Test
    fun getFavoriteShow() {
        val dummyShowFavorite = pagedList
        `when`(dummyShowFavorite.size).thenReturn(5)
        val show = MutableLiveData<PagedList<ShowEntity>>()
        show.value = dummyShowFavorite
        `when`(movieRepository.getFavoriteShow()).thenReturn(show)
        val favoriteShowData = viewModel.getFavoriteShow().value
        verify(movieRepository).getFavoriteShow()
        assertNotNull(favoriteShowData)
        assertEquals(5, favoriteShowData?.size)
        viewModel.getFavoriteShow().observeForever(observer)
        verify(observer).onChanged(dummyShowFavorite)
    }
}