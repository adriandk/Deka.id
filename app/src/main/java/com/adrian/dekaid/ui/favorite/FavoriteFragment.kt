package com.adrian.dekaid.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrian.dekaid.R
import com.adrian.dekaid.ui.favorite.movie.FavoriteMovieFragment
import com.adrian.dekaid.ui.favorite.show.FavoriteShowFragment
import com.adrian.dekaid.ui.home.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val fragmentList = listOf(FavoriteMovieFragment(), FavoriteShowFragment())
            val tabTitle = listOf(
                resources.getString(R.string.movie_tabs),
                resources.getString(R.string.tv_show_tabs)
            )
            viewpager_favorite.adapter =
                ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
            TabLayoutMediator(tab_layout, viewpager_favorite) { tab, position ->
                tab.text = tabTitle[position]
            }.attach()
        }
    }
}