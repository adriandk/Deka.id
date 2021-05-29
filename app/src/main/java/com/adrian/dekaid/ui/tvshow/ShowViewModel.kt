package com.adrian.dekaid.ui.tvshow

import androidx.lifecycle.ViewModel
import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.utils.DataDummy

class ShowViewModel : ViewModel() {
    fun getShow(): List<MovieData> = DataDummy.getShowData()
}