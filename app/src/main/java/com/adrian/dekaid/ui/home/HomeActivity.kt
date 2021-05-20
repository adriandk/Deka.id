package com.adrian.dekaid.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.adrian.dekaid.R
import com.adrian.dekaid.ui.movie.MovieFragment
import com.adrian.dekaid.ui.tvshow.ShowFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val movieFragment = MovieFragment()
    private val tvShowFragment = ShowFragment()
    private val fragmentManager = supportFragmentManager
    var fragment: Fragment = movieFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentManager.beginTransaction().add(R.id.frame_layout, tvShowFragment)
            .hide(tvShowFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame_layout, movieFragment).commit()

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movie -> {
                    fragmentManager.beginTransaction().hide(fragment).show(movieFragment).commit()
                    fragment = tvShowFragment
                }
                R.id.tvshow -> {
                    fragmentManager.beginTransaction().hide(fragment).show(tvShowFragment).commit()
                    fragment = movieFragment
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}