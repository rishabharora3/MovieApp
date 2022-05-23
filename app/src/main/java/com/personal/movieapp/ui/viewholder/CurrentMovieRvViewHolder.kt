package com.personal.movieapp.ui.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.personal.movieapp.data.CurrentMovie
import com.personal.movieapp.ui.adapter.CurrentMovieAdapter
import kotlinx.android.synthetic.main.current_movie_list_rv.view.*

/**
 * Created by Rishabh
 */
class CurrentMovieRvViewHolder(private val view: View, mContext: Context?) :
    RecyclerView.ViewHolder(view) {
    private val currentMovieAdapter = CurrentMovieAdapter()

    init {
        view.apply {
            if (rvCurrentMovieList.adapter == null) {
                rvCurrentMovieList.layoutManager =
                    LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                rvCurrentMovieList.adapter = currentMovieAdapter
            }
        }
    }

    fun setCurrentMovieRv(currentMovieList: List<CurrentMovie>?) {
        view.apply {
            currentMovieAdapter.setData(currentMovieList)
        }
    }
}