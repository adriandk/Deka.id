package com.adrian.dekaid.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.dekaid.R
import com.adrian.dekaid.ui.detail.DetailActivity
import com.adrian.dekaid.ui.detail.DetailViewModel.Companion.MOVIE
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]

            val movie = viewModel.getMovie()
            val movieAdapter = MovieAdapter()

            movieAdapter.setShow(movie)
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.MOVIE_ID, it.movieId)
                intent.putExtra(DetailActivity.MOVIE_CATEGORY, MOVIE)
                startActivity(intent)
            }

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)
            rv_movie.adapter = movieAdapter
        }
    }

}