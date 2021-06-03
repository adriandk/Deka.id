package com.adrian.dekaid.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Formatter {

    @SuppressLint("SimpleDateFormat")
    fun getYear(date: String): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val yearFormat = SimpleDateFormat("yyyy")
        return simpleDateFormat.parse(date)?.let { yearFormat.format(it) }
    }

}