package com.personal.movieapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.personal.movieapp.data.ApiRequestModel
import com.personal.movieapp.data.MovieDetailModel
import com.personal.movieapp.data.repository.MovieRepository
import com.personal.movieapp.utils.AppConstants
import com.personal.movieapp.utils.Resource

class MovieDetailViewModel : ViewModel() {
    private var movieDetailLiveData = MutableLiveData<Resource<MovieDetailModel>>()
    private val movieRepository = MovieRepository()
    private var movieId: String = ""

    fun getMovieDetailLiveData(): LiveData<Resource<MovieDetailModel>> = movieDetailLiveData

    fun setMovieId(movieId: String) {
        this.movieId = movieId
        movieDetailLiveData = fetchMovieDetail(getMovieDetailRequestModel())
    }

    private fun fetchMovieDetail(requestModel: ApiRequestModel): MutableLiveData<Resource<MovieDetailModel>> {
        return movieRepository.getMovieDetails(movieId, requestModel)
    }

    private fun getMovieDetailRequestModel(): ApiRequestModel {
        val hashMap = HashMap<String, String>()
        hashMap[AppConstants.API_KEY] = AppConstants.API_KEY_VALUE
        return ApiRequestModel(hashMap)
    }
}