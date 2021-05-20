package com.adrian.dekaid.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adrian.dekaid.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_DETAIL = "Data Detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}