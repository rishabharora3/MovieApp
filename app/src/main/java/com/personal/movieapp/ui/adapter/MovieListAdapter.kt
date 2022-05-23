package com.personal.movieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.personal.movieapp.R
import com.personal.movieapp.data.CurrentMovie
import com.personal.movieapp.data.ErrorModel
import com.personal.movieapp.data.PopularMovie
import com.personal.movieapp.ui.viewholder.*

/**
 * Created by Rishabh on 13-12-2020.
 */
class MovieListAdapter(private val movieList: MutableList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.mContext = parent.context
        when (viewType) {
            R.layout.movie_list_logo -> {
                return MovieListLogoViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.movie_list_logo,
                        parent,
                        false
                    )
                )
            }
            R.layout.movie_list_progress_bar -> {
                return MovieListProgressBarViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.movie_list_progress_bar,
                        parent,
                        false
                    )
                )
            }
            R.layout.movie_list_header -> {
                return MovieListHeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.movie_list_header,
                        parent,
                        false
                    ), mContext
                )
            }
            R.layout.current_movie_list_rv -> {
                return CurrentMovieRvViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.current_movie_list_rv,
                        parent,
                        false
                    ),
                    mContext
                )
            }
            else -> {
                return PopularMovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.popular_movie_item,
                        parent,
                        false
                    ), mContext
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (this) {
                is MovieListHeaderViewHolder -> {
                    setHeaderText(position)
                }
                is CurrentMovieRvViewHolder -> {
                    setCurrentMovieRv(movieList[position] as List<CurrentMovie>)
                }
                is PopularMovieViewHolder -> {
                    bind(movieList[position] as PopularMovie)
                }
                is MovieListProgressBarViewHolder -> {
                    setData(movieList[position] as ErrorModel)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (movieList[position]) {
            is Int -> {
                when (movieList[position]) {
                    R.layout.movie_list_logo -> return R.layout.movie_list_logo
                    R.layout.movie_list_header -> return R.layout.movie_list_header
                }
            }
            is ErrorModel -> {
                return R.layout.movie_list_progress_bar
            }
            is List<*> -> {
                return R.layout.current_movie_list_rv
            }
            else -> return R.layout.popular_movie_item
        }
        return R.layout.popular_movie_item
    }

    fun setPopularMovieList(popularMovieList: List<PopularMovie>?) {
        popularMovieList?.let {
            if (it.isNotEmpty()) {
                if (movieList[movieList.size - 1] is ErrorModel)
                    movieList.removeAt(movieList.size - 1)
                movieList.addAll(it)
                notifyDataSetChanged()
            }
        }
    }

    fun setCurrentMovieList(currentMovieList: List<CurrentMovie>?) {
        currentMovieList?.let {
            if (it.isNotEmpty()) {
                if (movieList[2] is ErrorModel)
                    movieList.removeAt(2)
                movieList.add(2, it)
                notifyDataSetChanged()
            }
        }
    }

    fun setMovieListError(pos: Int, string: String) {
        if (movieList[pos] is ErrorModel) {
            movieList[pos] = ErrorModel(string, false)
            notifyItemChanged(pos)
        }
    }
}
