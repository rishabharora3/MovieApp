package com.personal.movieapp.ui.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.personal.movieapp.R
import kotlinx.android.synthetic.main.movie_list_header.view.*

class MovieListHeaderViewHolder(private val view: View, private val mContext: Context?) :
    RecyclerView.ViewHolder(view) {

    fun setHeaderText(pos: Int) {
        view.apply {
            if (pos == 1) {
                tvHeader.text = mContext?.resources?.getString(R.string.playing_now)
            } else
                tvHeader.text = mContext?.resources?.getString(R.string.most_popular)
        }
    }
}


