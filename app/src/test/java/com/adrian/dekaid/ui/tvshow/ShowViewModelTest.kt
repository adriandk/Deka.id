package com.adrian.dekaid.ui.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ShowViewModelTest {

    private lateinit var viewModel: ShowViewModel

    @Before
    fun setUp() {
        viewModel = ShowViewModel()
    }

    @Test
    fun getShow() {
        val showEntities = viewModel.getShow()
        assertNotNull(showEntities)
        assertEquals(10, showEntities.size)
    }
}