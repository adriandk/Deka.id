package com.adrian.dekaid.ui.favorite.movie

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
import com.adrian.dekaid.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.favorite_movie_fragment.*

class FavoriteMovieFragment : Fragment() {

    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var movieAdapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[FavoriteMovieViewModel::class.java]
            movieAdapter = FavoriteMovieAdapter()
            setDataToAdapter()
            rv_movie_favorite.layoutManager = LinearLayoutManager(context)
            rv_movie_favorite.setHasFixedSize(true)
            rv_movie_favorite.adapter = movieAdapter
        }
    }

    private fun setDataToAdapter() {
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, {
            if (it != null) {
                movieAdapter.submitList(it)
                movieAdapter.setData(it)
                movieAdapter.onItemClick = { movieData ->
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.MOVIE_DATA, movieData.movieId)
                    intent.putExtra(DetailActivity.MOVIE_CATEGORY, DetailActivity.MOVIE)
                    startActivity(intent)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setDataToAdapter()
    }

}