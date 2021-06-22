package com.adrian.dekaid.ui.tvshow

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
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.Resource
import com.adrian.dekaid.ui.detail.DetailActivity
import com.adrian.dekaid.utils.SortUtils.NEWEST
import com.adrian.dekaid.utils.SortUtils.OLDEST
import com.adrian.dekaid.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_show.*

class ShowFragment : Fragment() {

    private lateinit var viewModel: ShowViewModel
    private lateinit var showAdapter: ShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_show, container, false)
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
            )[ShowViewModel::class.java]
            showAdapter = ShowAdapter()
            viewModel.getShow(NEWEST).observe(viewLifecycleOwner, showObserver)

            rv_tvshow.layoutManager = LinearLayoutManager(context)
            rv_tvshow.setHasFixedSize(true)
            rv_tvshow.adapter = showAdapter
        }
    }

    private val showObserver = Observer<Resource<PagedList<ShowEntity>>> {
        if (it != null) {
            when (it) {
                is Resource.Loading -> progressBar(true)
                is Resource.Success -> {
                    progressBar(false)
                    showAdapter.submitList(it.data)
                    showAdapter.setData(it.data)
                    showAdapter.onItemClick = { showData ->
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.MOVIE_DATA, showData.showId)
                        intent.putExtra(DetailActivity.MOVIE_CATEGORY, DetailActivity.SHOW)
                        startActivity(intent)
                    }
                    showAdapter.notifyDataSetChanged()
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
        viewModel.getShow(sort).observe(this, showObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun progressBar(bar: Boolean) {
        show_bar.isVisible = bar
        rv_tvshow.isInvisible = bar
    }

}