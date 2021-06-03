package com.adrian.dekaid.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.databinding.ActivityDetailBinding
import com.adrian.dekaid.utils.Formatter
import com.adrian.dekaid.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding

    companion object {
        const val MOVIE_ID = "Movie ID"
        const val MOVIE_CATEGORY = "Category Movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance()
        )[DetailViewModel::class.java]

        progressBar(true)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(MOVIE_ID)
            val movieCategory = extras.getString(MOVIE_CATEGORY)
            viewModel.getMovie(movieId, movieCategory.toString())
            viewModel.getMovieDetail().observe(this, {
                populateDataDetail(it)
            })
        }
    }

    private fun progressBar(bar: Boolean) {
        detail_bar.isVisible = bar
    }

    private fun populateDataDetail(movies: MovieData) {
        movie_title_detail.text = movies.movieTitle
        duration_detail.text = movies.movieDuration.toString()
        release_year.text = Formatter.getYear(movies.movieReleaseYear)
        vote_detail.text = movies.movieVote.toString()
        movie_sinopsis.text = movies.movieDescription

        Glide.with(this)
            .load(movies.posterLink)
            .into(detailBinding.imageDetail)

        detailBinding.imageDetail.tag = movies.movieImage

        progressBar(false)
    }
}