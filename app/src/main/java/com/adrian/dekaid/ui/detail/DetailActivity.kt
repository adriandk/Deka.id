package com.adrian.dekaid.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adrian.dekaid.R
import com.adrian.dekaid.data.MovieData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "Movie ID"
        const val MOVIE_CATEGORY = "Category Movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(MOVIE_ID)
            val movieCategory = extras.getString(MOVIE_CATEGORY)
            if (movieId != null) {
                viewModel.getMovie(movieId, movieCategory.toString())
                val movies = viewModel.getMovieDetail()
                populateDataDetail(movies)
            }
        }
    }

    private fun populateDataDetail(movies: MovieData) {
        movie_title_detail.text = movies.movieTitle
        duration_detail.text = movies.movieDuration
        release_year.text = movies.movieReleaseYear
        genre_detail.text = movies.movieGenre
        movie_sinopsis.text = movies.movieDescription

        Glide.with(this)
            .load(movies.movieImage)
            .error(R.drawable.broken_image)
            .into(image_detail)
    }
}