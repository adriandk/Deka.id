package com.adrian.dekaid.ui.detail

import com.adrian.dekaid.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.getMovieData()[0]
    private val movieId = dummyMovie.movieId

    private val dummyShow = DataDummy.getShowData()[0]
    private val showId = dummyShow.movieId

    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel()
        viewModel.getMovie(movieId, "movie")
    }

    @Test
    fun getMovieDetail() {
        viewModel.getMovie(dummyMovie.movieId, "movie")
        val movieEntity = viewModel.getMovieDetail()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.movieTitle, movieEntity.movieTitle)
        assertEquals(dummyMovie.movieDuration, movieEntity.movieDuration)
        assertEquals(dummyMovie.movieGenre, movieEntity.movieGenre)
        assertEquals(dummyMovie.movieDescription, movieEntity.movieDescription)
        assertEquals(dummyMovie.movieReleaseYear, movieEntity.movieReleaseYear)
        assertEquals(dummyMovie.movieImage, movieEntity.movieImage)
    }

    @Before
    fun setUpShow() {
        viewModel = DetailViewModel()
        viewModel.getMovie(showId, "tv show")
    }

    @Test
    fun getShowDetail() {
        viewModel.getMovie(dummyShow.movieId, "tv show")
        val showEntity = viewModel.getMovieDetail()
        assertNotNull(showEntity)
        assertEquals(dummyShow.movieId, showEntity.movieId)
        assertEquals(dummyShow.movieTitle, showEntity.movieTitle)
        assertEquals(dummyShow.movieDuration, showEntity.movieDuration)
        assertEquals(dummyShow.movieGenre, showEntity.movieGenre)
        assertEquals(dummyShow.movieDescription, showEntity.movieDescription)
        assertEquals(dummyShow.movieReleaseYear, showEntity.movieReleaseYear)
        assertEquals(dummyShow.movieImage, showEntity.movieImage)
    }
}