package com.adrian.dekaid.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.adrian.dekaid.R
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.databinding.ActivityDetailBinding
import com.adrian.dekaid.utils.Formatter
import com.adrian.dekaid.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
            val movieCategory = extras.getString(MOVIE_CATEGORY).toString()
            viewModel.getMovie(movieId, movieCategory)
            viewModel.getMovieDetail().observe(this, {
                populateDataDetail(it, movieCategory)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateDataDetail(movies: MovieData, movieCategory: String) {
        progressBar(false)
        if (movieCategory == "movie") {
            movie_title_detail.text = movies.movieTitle
            duration_detail.text = "${movies.movieDuration} min"
            release_year.text = Formatter.getYear(movies.movieReleaseYear)
        } else {
            movie_title_detail.text = movies.movieName
            duration_detail.text = "${movies.showSeason} seasons"
            release_year.text = Formatter.getYear(movies.movieFirstAir)
        }
        vote_detail.text = movies.movieVote.toString()
        movie_sinopsis.text = movies.movieDescription

        Glide.with(this)
            .load(movies.posterLink)
            .apply(RequestOptions.placeholderOf(R.drawable.image_icon))
            .error(R.drawable.broken_image)
            .into(detailBinding.imageDetail)

        detailBinding.imageDetail.tag = movies.posterLink
    }

    private fun progressBar(bar: Boolean) {
        detail_bar.isVisible = bar
    }
}