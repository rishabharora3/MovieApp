package com.personal.movieapp.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.personal.movieapp.R
import com.personal.movieapp.data.CurrentMovie
import com.personal.movieapp.data.ErrorModel
import com.personal.movieapp.data.PopularMovie
import com.personal.movieapp.ui.adapter.MovieListAdapter
import com.personal.movieapp.ui.viewmodel.MovieListViewModel
import com.personal.movieapp.utils.AppConstants
import com.personal.movieapp.utils.Status
import kotlinx.android.synthetic.main.movie_list_fragment.*


class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var rvMovieLLManager: LinearLayoutManager
    private lateinit var mContext: Context
    private val movieListViewModel by viewModels<MovieListViewModel>()
    private lateinit var movieListAdapter: MovieListAdapter
    private var isLoading = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        setListeners()
    }

    private fun init() {
        rvMovieLLManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rvMovieList.layoutManager = rvMovieLLManager
        setMovieListAdapter()
        rvMovieList.adapter = movieListAdapter
    }

    private fun setMovieListAdapter() {
        val movieList = mutableListOf<Any>()
        movieList.add(R.layout.movie_list_logo)
        movieList.add(R.layout.movie_list_header)
        movieList.add(ErrorModel("", true))
        movieList.add(R.layout.movie_list_header)
        movieList.add(ErrorModel("", true))
        movieListAdapter = MovieListAdapter(movieList)
    }

    private fun setListeners() {
        observeImageConfigurationLivaData()
        setRvPopularMovieListScrollListener()
    }

    private fun observeCurrentMovieLivaData() {
        movieListViewModel.getCurrentMovieLiveData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                }

                Status.ERROR -> {
                    movieListAdapter.setMovieListError(2, getString(R.string.toast_api_error))
                }

                Status.SUCCESS -> {
                    val currentMovieList = it.data?.results as MutableList<CurrentMovie>
                    if (currentMovieList.isEmpty()) {
                        movieListAdapter.setMovieListError(2, getString(R.string.toast_no_results))
                    }
                    movieListAdapter.setCurrentMovieList(currentMovieList)
                }
            }
        })
    }

    private fun observePopularMovieLivaData() {
        movieListViewModel.getPopularMovieLivaData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                }

                Status.ERROR -> {
                    isLoading = false
                    movieListAdapter.setMovieListError(4, getString(R.string.toast_api_error))
                }

                Status.SUCCESS -> {
                    isLoading = false
                    movieListViewModel.updateTotalPages(it.data)
                    val list = it.data?.results as MutableList<PopularMovie>
                    if (list.isEmpty())
                        movieListAdapter.setMovieListError(4, getString(R.string.toast_no_results))
                    movieListAdapter.setPopularMovieList(list)
                }
            }
        })
    }

    private fun observeImageConfigurationLivaData() {
        movieListViewModel.getImageConfigurationLivaData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    fetchMoviesList()
                }
                Status.SUCCESS -> {
                    movieListViewModel.setImageUrl(it)
                    fetchMoviesList()
                }
            }
        })
    }

    private fun fetchMoviesList() {
        movieListViewModel.fetchMoviesList()
        observeCurrentMovieLivaData()
        observePopularMovieLivaData()
    }

    private fun setRvPopularMovieListScrollListener() {
        rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = rvMovieLLManager.childCount
                val totalItemCount = rvMovieLLManager.itemCount
                val firstVisibleItemPosition =
                    rvMovieLLManager.findFirstVisibleItemPosition()
                if (!isLoading && !movieListViewModel.isLastPage() && (visibleItemCount +
                            firstVisibleItemPosition) >= totalItemCount - 1 &&
                    firstVisibleItemPosition >= 0 && totalItemCount - 4 >= AppConstants.SINGLE_PAGE_ITEM_COUNT
                ) {
                    isLoading = true
                    movieListViewModel.getPagedData()
                }
            }
        })
    }
}