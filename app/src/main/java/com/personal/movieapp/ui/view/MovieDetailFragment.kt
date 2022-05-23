package com.personal.movieapp.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.personal.movieapp.R
import com.personal.movieapp.data.Genres
import com.personal.movieapp.data.MovieDetailModel
import com.personal.movieapp.ui.custom.GenreView
import com.personal.movieapp.ui.viewmodel.MovieDetailViewModel
import com.personal.movieapp.utils.AppConstants
import com.personal.movieapp.utils.DateTimeUtil
import com.personal.movieapp.utils.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance(movieId: String): MovieDetailFragment {
            val bundle = Bundle()
            bundle.putString(AppConstants.MOVIE_ID, movieId)
            val fragment = MovieDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val movieDetailViewModel by viewModels<MovieDetailViewModel>()
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        setListeners()
    }

    private fun init() {
        arguments?.getString(AppConstants.MOVIE_ID, "")?.let { movieDetailViewModel.setMovieId(it) }
        observePopularMovieDetailData()
    }

    private fun observePopularMovieDetailData() {
        movieDetailViewModel.getMovieDetailLiveData().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                }

                Status.ERROR -> {

                }

                Status.SUCCESS -> {
                    val movieDetailModel = it.data
                    setMoviePoster(movieDetailModel?.poster_path)
                    tvTitle.text = movieDetailModel?.title
                    setDateAndRunTime(movieDetailModel)
                    tvDescription.text = movieDetailModel?.overview
                    setGenres(movieDetailModel?.genres)
                }
            }
        }
    }

    private fun setDateAndRunTime(movieDetailModel: MovieDetailModel?) {
        val hours: Int =
            movieDetailModel?.runtime?.div(60) ?: 0
        val minutes: Int = movieDetailModel?.runtime?.rem(60) ?: 0
        val date = DateTimeUtil.genericDateFormatter(
            movieDetailModel?.release_date, DateTimeUtil.date_YYYY_MM_DD,
            DateTimeUtil.date_MMM_DD_YYYY
        )
        val string = StringBuilder()
        string.append(date).append("  -  ").append(hours).append("h ").append(minutes).append("m")
        tvReleaseDate.text = string
    }

    private fun setMoviePoster(posterPath: String?) {
        Glide.with(ivMoviePoster).load(AppConstants.IMAGE_URL + posterPath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop().placeholder(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.drawable_placeholder
                )
            ).into(ivMoviePoster)
    }

    private fun setGenres(genres: List<Genres>?) {
        genres?.apply {
            for (item in this) {
                val genre = GenreView(mContext)
                genre.setText(item.name)
                flGenres.addView(genre)
            }
        }
    }

    private fun setListeners() {
        ivBack.setOnClickListener {
            (mContext as MainActivity).supportFragmentManager.popBackStack()
        }
    }

}