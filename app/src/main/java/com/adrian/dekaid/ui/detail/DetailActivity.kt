package com.adrian.dekaid.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.adrian.dekaid.R
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.Resource
import com.adrian.dekaid.databinding.ActivityDetailBinding
import com.adrian.dekaid.utils.Formatter
import com.adrian.dekaid.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val MOVIE_DATA = "Movie Data"
        const val MOVIE_CATEGORY = "Movie Category"

        const val MOVIE = "Movie"
        const val SHOW = "Show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        detailViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailViewModel::class.java]
        val category = intent.getStringExtra(MOVIE_CATEGORY)

        progressBar(true)
        if (category == MOVIE) {
            val movieId = intent.getIntExtra(MOVIE_DATA, 0)
            detailViewModel.getDetail(movieId, MOVIE)
            detailViewModel.detailDataMovie().observe(this, {
                if (it != null) {
                    when(it) {
                        is Resource.Loading -> progressBar(true)
                        is Resource.Error -> progressBar(true)
                        is Resource.Success -> {
                            progressBar(false)
                            showDetailMovie(it.data)
                        }
                    }
                }
            })
        } else {
            val showId = intent.getIntExtra(MOVIE_DATA,0)
            detailViewModel.getDetail(showId, SHOW)
            detailViewModel.detailDataShow().observe(this, {
                if (it != null) {
                    when(it) {
                        is Resource.Loading -> progressBar(true)
                        is Resource.Error -> progressBar(true)
                        is Resource.Success -> {
                            progressBar(false)
                            showDetailShow(it.data)
                        }
                    }
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailMovie(movie: MovieEntity?) {
        movie?.let {
            progressBar(false)
            movie_title_detail.text = movie.movieTitle
            duration_detail.text = "${movie.movieDuration} min"
            release_year.text = Formatter.getYear(movie.movieReleaseYear)
            vote_detail.text = movie.movieVote.toString()
            movie_sinopsis.text = movie.movieDescription

            Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.movieImage)
            .apply(RequestOptions.placeholderOf(R.drawable.image_icon))
            .error(R.drawable.broken_image)
            .into(detailBinding.imageDetail)

            detailBinding.imageDetail.tag = movie.movieImage

            var statusFavorite = movie.isFavorite
            setStatusFavorite(statusFavorite)
            favorite_button.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(movie, statusFavorite)
                setStatusFavorite(statusFavorite)
                Toast.makeText(this, "berhasil", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailShow(show: ShowEntity?) {
        show?.let {
            progressBar(false)
            movie_title_detail.text = show.showName
            duration_detail.text = "${show.showSeason} seasons"
            release_year.text = Formatter.getYear(show.showFirstAir)
            vote_detail.text = show.showVote.toString()
            movie_sinopsis.text = show.showDescription

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + show.showImage)
                .apply(RequestOptions.placeholderOf(R.drawable.image_icon))
                .error(R.drawable.broken_image)
                .into(detailBinding.imageDetail)

            detailBinding.imageDetail.tag = show.showImage

            var statusFavorite = show.isFavorite
            setStatusFavorite(statusFavorite)
            favorite_button.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteShow(show, statusFavorite)
                setStatusFavorite(statusFavorite)
                Toast.makeText(this, "berhasil", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun progressBar(bar: Boolean) {
        detail_bar.isVisible = bar
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            favorite_button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite_icon))
        } else {
            favorite_button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite_outline_icon))
        }
    }

}