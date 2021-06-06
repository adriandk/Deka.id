package com.adrian.dekaid.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adrian.dekaid.data.MovieRepository
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.utils.DataDummy
import com.google.common.truth.Truth
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
class ShowViewModelTest {

    private lateinit var viewModel: ShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieData>>

    @Before
    fun setUp() {
        viewModel = ShowViewModel(movieRepository)
    }

    @Test
    fun getShow() {
        val dummyData = DataDummy.getShowData()
        val show = MutableLiveData<List<MovieData>>()
        show.value = dummyData

        `when`(movieRepository.loadAllShow()).thenReturn(show)
        val showData = viewModel.getShow().value
        verify(movieRepository).loadAllShow()
        assertNotNull(showData)
        Truth.assertThat(showData?.size).isEqualTo(show.value!!.size)

        viewModel.getShow().observeForever(observer)
        verify(observer).onChanged(showData)
    }
}