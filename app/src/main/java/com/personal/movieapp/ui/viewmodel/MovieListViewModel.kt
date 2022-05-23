package com.personal.movieapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.personal.movieapp.data.*
import com.personal.movieapp.data.repository.MovieRepository
import com.personal.movieapp.utils.AppConstants
import com.personal.movieapp.utils.Resource

class MovieListViewModel : ViewModel() {
    private var currentMovieLiveData = MutableLiveData<Resource<CurrentMovieListModel>>()
    private var popularMovieLivaData = MutableLiveData<Resource<PopularMovieListModel>>()
    private var configurationLivaData = MutableLiveData<Resource<ConfigurationModel>>()
    private val movieRepository = MovieRepository()
    private var currentPageNumber = 0
    private var totalPages = 0

    init {
        configurationLivaData = fetchImageConfiguration(getImageConfigurationRequestModel())
    }


    fun getCurrentMovieLiveData(): LiveData<Resource<CurrentMovieListModel>> = currentMovieLiveData
    fun getPopularMovieLivaData(): LiveData<Resource<PopularMovieListModel>> = popularMovieLivaData
    fun getImageConfigurationLivaData(): LiveData<Resource<ConfigurationModel>> =
        configurationLivaData


    private fun fetchImageConfiguration(requestModel: ApiRequestModel): MutableLiveData<Resource<ConfigurationModel>> {
        return movieRepository.getImageConfiguration(requestModel)
    }

    private fun fetchCurrentMovieList(requestModel: ApiRequestModel): MutableLiveData<Resource<CurrentMovieListModel>> {
        return movieRepository.getCurrentMovieList(requestModel)
    }

    private fun fetchPopularMovieList(requestModel: ApiRequestModel): MutableLiveData<Resource<PopularMovieListModel>> {
        return movieRepository.getPopularMovieList(requestModel, popularMovieLivaData)
    }

    fun getPagedData() {
        popularMovieLivaData =
            fetchPopularMovieList(getPopularMovieListRequestModel(++currentPageNumber))
    }

    private fun getImageConfigurationRequestModel(): ApiRequestModel {
        val hashMap = HashMap<String, String>()
        hashMap[AppConstants.API_KEY] = AppConstants.API_KEY_VALUE
        return ApiRequestModel(hashMap)
    }

    private fun getCurrentMovieListRequestModel(): ApiRequestModel {
        val hashMap = HashMap<String, String>()
        hashMap[AppConstants.LANGUAGE] = AppConstants.LANGUAGE_VALUE
        hashMap[AppConstants.PAGE] = AppConstants.PAGE_VALUE
        hashMap[AppConstants.API_KEY] = AppConstants.API_KEY_VALUE
        return ApiRequestModel(hashMap)
    }

    private fun getPopularMovieListRequestModel(
        currentPageNumber: Int
    ): ApiRequestModel {
        val hashMap = HashMap<String, String>()
        hashMap[AppConstants.LANGUAGE] = AppConstants.LANGUAGE_VALUE
        hashMap[AppConstants.PAGE] = currentPageNumber.toString()
        hashMap[AppConstants.API_KEY] = AppConstants.API_KEY_VALUE
        return ApiRequestModel(hashMap)
    }

    fun isLastPage(): Boolean {
        return currentPageNumber == totalPages
    }

    fun updateTotalPages(popularMovieListModel: PopularMovieListModel?) {
        popularMovieListModel?.let {
            totalPages = it.total_pages ?: 0
        }
    }

    fun fetchMoviesList() {
        currentMovieLiveData = fetchCurrentMovieList(getCurrentMovieListRequestModel())
        getPagedData()
    }

    fun setImageUrl(it: Resource<ConfigurationModel>) {
        AppConstants.BASE_IMAGE_URL = it.data?.images?.secure_base_url
        val posterSizes: List<String>? = it.data?.images?.poster_sizes
        posterSizes?.let { list ->
            if (list.size >= 3)
                AppConstants.IMAGE_SIZE = list[list.size - 3]
        }
        AppConstants.IMAGE_URL =
            "${AppConstants.BASE_IMAGE_URL}/${AppConstants.IMAGE_SIZE}/"
    }
}