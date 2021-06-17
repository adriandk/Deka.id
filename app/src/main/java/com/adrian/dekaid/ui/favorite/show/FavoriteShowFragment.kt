package com.adrian.dekaid.ui.favorite.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adrian.dekaid.R

class FavoriteShowFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteShowFragment()
    }

    private lateinit var viewModel: FavoriteShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteShowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}