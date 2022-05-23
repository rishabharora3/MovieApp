package com.personal.movieapp.ui.viewholder

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.personal.movieapp.R
import com.personal.movieapp.data.CurrentMovie
import com.personal.movieapp.ui.view.MainActivity
import com.personal.movieapp.ui.view.MovieDetailFragment
import com.personal.movieapp.utils.AppConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.current_movie_item.view.*

/**
 * Created by Rishabh
 */
class CurrentMovieViewHolder(private val view: View, private val mContext: Context?) :
    RecyclerView.ViewHolder(view) {

    fun bind(currentMovie: CurrentMovie?) {
        view.apply {
            currentMovie?.apply {
                setIcon(this)
                setListener(this)
            }
        }
    }

    private fun View.setListener(currentMovie: CurrentMovie) {
        ivCurrentMovie.setOnClickListener {
            addMovieDetailFragment(currentMovie)
        }
    }

    private fun addMovieDetailFragment(currentMovie: CurrentMovie) {
        val transaction = (mContext as MainActivity).supportFragmentManager.beginTransaction()
        transaction.addToBackStack("MovieDetailFragment")
        transaction.add(R.id.fl_home, MovieDetailFragment.newInstance(currentMovie.id.toString()))
        transaction.commit()
    }

    private fun View.setIcon(currentMovie: CurrentMovie?) {
        Glide.with(ivCurrentMovie).load(AppConstants.IMAGE_URL + currentMovie?.poster_path)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop().placeholder(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.drawable_placeholder
                )
            ).into(ivCurrentMovie)
    }
}