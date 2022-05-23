package com.personal.movieapp.ui.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.personal.movieapp.R
import com.personal.movieapp.data.PopularMovie
import com.personal.movieapp.ui.view.MainActivity
import com.personal.movieapp.ui.view.MovieDetailFragment
import com.personal.movieapp.utils.AppConstants
import com.personal.movieapp.utils.DateTimeUtil
import com.personal.movieapp.utils.TopAlignSuperscriptSpan
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.popular_movie_item.view.*


/**
 * Created by Rishabh
 */
class PopularMovieViewHolder(private val view: View, private val mContext: Context?) :
    RecyclerView.ViewHolder(view) {
    private var drawableGreen: Drawable? =
        mContext?.let { ContextCompat.getDrawable(it, R.drawable.progress_bar_green) }
    private var drawableYellow: Drawable? =
        mContext?.let { ContextCompat.getDrawable(it, R.drawable.progress_bar_yellow) }

    fun bind(popularMovie: PopularMovie?) {
        view.apply {
            popularMovie?.apply {
                setIcon(this)
                tvTitle.text = title
                tvReleaseDate.text = DateTimeUtil.genericDateFormatter(
                    release_date, DateTimeUtil.date_YYYY_MM_DD,
                    DateTimeUtil.date_MMM_DD_YYYY
                )
                setRunTime(this)
                vote_average?.let {
                    if ((it * 10).toInt() >= 50)
                        rating.progressDrawable =
                            drawableGreen
                    else
                        rating.progressDrawable =
                            drawableYellow
                    rating.progress = (it * 10).toInt()
                    setPercentage((it * 10).toInt().toString())
                }
                setListener(this)
            }

        }
    }

    private fun View.setListener(popularMovie: PopularMovie) {
        clPopularMovie.setOnClickListener {
            addMovieDetailFragment(popularMovie)
        }
    }

    private fun addMovieDetailFragment(popularMovie: PopularMovie) {
        val transaction = (mContext as MainActivity).supportFragmentManager.beginTransaction()
        transaction.addToBackStack("MovieDetailFragment")
        transaction.add(R.id.fl_home, MovieDetailFragment.newInstance(popularMovie.id.toString()))
        transaction.commit()
    }

    private fun View.setPercentage(percent: String) {
        val s = "$percent %"
        val percentSpannable = SpannableString(s)
        percentSpannable.setSpan(
            TopAlignSuperscriptSpan(0.35.toFloat()),
            s.indexOf(" "),
            s.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvPercentage.text = percentSpannable
    }

    private fun View.setIcon(popularMovie: PopularMovie?) {
        Glide.with(ivMoviePoster).load(AppConstants.IMAGE_URL + popularMovie?.poster_path)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop().placeholder(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.drawable_placeholder
                )
            ).into(ivMoviePoster)
    }

    private fun View.setRunTime(popularMovie: PopularMovie?) {
        if (popularMovie?.runtime == null) {
            tvDuration.text = "-"
            return
        }
        popularMovie.runtime.let {
            val hours: Int =
                it.div(60)
            val minutes: Int = it.rem(60)
            val string = StringBuilder()
            string.append(hours).append("h ").append(minutes).append("m")
            tvDuration.text = string
        }
    }
}