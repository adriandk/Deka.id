package com.adrian.dekaid.ui.tvshow

import com.adrian.dekaid.data.MovieData
import com.adrian.dekaid.utils.DataDummy

class ShowViewModel {
    fun getShow(): List<MovieData> = DataDummy.getShowData()
}