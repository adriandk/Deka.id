package com.adrian.dekaid.ui.favorite.show

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
import kotlinx.android.synthetic.main.favorite_show_fragment.*

class FavoriteShowFragment : Fragment() {

    private lateinit var viewModel: FavoriteShowViewModel
    private lateinit var showAdapter: FavoriteShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_show_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[FavoriteShowViewModel::class.java]
            showAdapter = FavoriteShowAdapter()
            setDataToAdapter()
            rv_show_favorite.layoutManager = LinearLayoutManager(context)
            rv_show_favorite.setHasFixedSize(true)
            rv_show_favorite.adapter = showAdapter
        }
    }

    private fun setDataToAdapter() {
        viewModel.getFavoriteShow().observe(viewLifecycleOwner, {
            if (it != null) {
                showAdapter.submitList(it)
                showAdapter.setData(it)
                showAdapter.onItemClick = { showData ->
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.MOVIE_DATA, showData.showId)
                    intent.putExtra(DetailActivity.MOVIE_CATEGORY, DetailActivity.SHOW)
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