package com.adrian.dekaid.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.dekaid.R
import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.remote.Resource
import com.adrian.dekaid.ui.detail.DetailActivity
import com.adrian.dekaid.ui.detail.DetailViewModel.Companion.MOVIE
import com.adrian.dekaid.utils.SortUtils.NEWEST
import com.adrian.dekaid.utils.SortUtils.OLDEST
import com.adrian.dekaid.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_movie, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            progressBar(true)
            viewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance(requireActivity())
            )[MovieViewModel::class.java]
            movieAdapter = MovieAdapter()
            viewModel.getMovie(NEWEST).observe(viewLifecycleOwner, movieObserver)

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)
            rv_movie.adapter = movieAdapter
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> {
        if (it != null) {
            when (it) {
                is Resource.Loading -> progressBar(true)
                is Resource.Success -> {
                    progressBar(false)
                    movieAdapter.submitList(it.data)
                    movieAdapter.setData(it.data)
                    movieAdapter.notifyDataSetChanged()
                    movieAdapter.onItemClick = {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.MOVIE_ID, it.movieId)
                        intent.putExtra(DetailActivity.MOVIE_CATEGORY, MOVIE)
                        startActivity(intent)
                    }
                }
                is Resource.Error -> progressBar(true)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.newest -> sort = NEWEST
            R.id.oldest -> sort = OLDEST
        }
        viewModel.getMovie(sort).observe(this, movieObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun progressBar(bar: Boolean) {
        movie_bar.isVisible = bar
        rv_movie.isInvisible = bar
    }

}