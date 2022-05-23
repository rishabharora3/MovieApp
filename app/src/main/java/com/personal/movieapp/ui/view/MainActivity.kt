package com.personal.movieapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.personal.movieapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            //The Activity is being created for the first time, so create and
            //add new fragments.
            addMovieListFragment()
        } else {
            //Otherwise, the activity is coming back after being destroyed.
            //The FragmentManager will restore the old Fragments so we don't
            //need to create any new ones here.
        }
    }

    private fun addMovieListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_home, MovieListFragment.newInstance())
        transaction.commit()
    }
}
