package com.adrian.dekaid.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.dekaid.R
import com.adrian.dekaid.ui.detail.DetailActivity
import com.adrian.dekaid.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.adrian.dekaid.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_show.*

class ShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance()
            )[ShowViewModel::class.java]
            val showAdapter = ShowAdapter()

            progressBar(true)
            viewModel.getShow().observe(viewLifecycleOwner, {
                showAdapter.setShow(it)
                showAdapter.onItemClick = {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.MOVIE_ID, it.movieId)
                    intent.putExtra(DetailActivity.MOVIE_CATEGORY, TV_SHOW)
                    startActivity(intent)
                }
                showAdapter.notifyDataSetChanged()
            })

            rv_tvshow.layoutManager = LinearLayoutManager(context)
            rv_tvshow.setHasFixedSize(true)
            rv_tvshow.adapter = showAdapter
        }
    }

    private fun progressBar(bar: Boolean) {
        show_bar.isVisible = bar
        rv_tvshow.isInvisible = bar
    }

}