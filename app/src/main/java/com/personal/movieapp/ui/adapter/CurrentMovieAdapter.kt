package com.personal.movieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.personal.movieapp.R
import com.personal.movieapp.data.CurrentMovie
import com.personal.movieapp.ui.viewholder.CurrentMovieViewHolder

/**
 * Created by Rishabh
 */
class CurrentMovieAdapter : RecyclerView.Adapter<CurrentMovieViewHolder>() {

    private var currentMovieList = mutableListOf<CurrentMovie>()
    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentMovieViewHolder {
        this.mContext = parent.context
        return CurrentMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.current_movie_item,
                parent,
                false
            ), mContext
        )
    }

    override fun getItemCount(): Int {
        return currentMovieList.size
    }

    override fun onBindViewHolder(holder: CurrentMovieViewHolder, position: Int) {
        holder.bind(currentMovieList[position])
    }

    fun setData(currentMovieList: List<CurrentMovie>?) {
        currentMovieList?.let {
            this.currentMovieList = it.toMutableList()
            notifyDataSetChanged()
        }
    }
}