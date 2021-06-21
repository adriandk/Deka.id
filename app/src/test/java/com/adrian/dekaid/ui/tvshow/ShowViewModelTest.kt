package com.adrian.dekaid.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowViewModelTest {

    private lateinit var viewModel: ShowViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<ShowEntity>>>

    @Mock
    lateinit var pagedList: PagedList<ShowEntity>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ShowViewModel(movieRepository)
    }

    @Test
    fun getShow() {
        val dummyShow = Resource.Success(pagedList)
        `when`(dummyShow.data?.size).thenReturn(3)
        val show = MutableLiveData<Resource<PagedList<ShowEntity>>>()
        show.value = dummyShow

        `when`(movieRepository.loadAllShow("NEWEST")).thenReturn(show)
        val showData = viewModel.getShow("NEWEST").value?.data
        verify(movieRepository).loadAllShow("NEWEST")
        assertNotNull(showData)
        Assert.assertEquals(3, showData?.size)

        viewModel.getShow("NEWEST").observeForever(observer)
        verify(observer).onChanged(dummyShow)
    }
}