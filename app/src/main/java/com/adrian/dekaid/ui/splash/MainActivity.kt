package com.adrian.dekaid.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.adrian.dekaid.R
import com.adrian.dekaid.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val splashTime: Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, splashTime)

    }
}