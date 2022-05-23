package com.personal.movieapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.personal.movieapp.data.*
import com.personal.movieapp.network.ApiResponseInterface
import com.personal.movieapp.network.NetworkUtils
import com.personal.movieapp.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Rishabh
 */
class MovieRepository {
    var apiResponseInterface: ApiResponseInterface = NetworkUtils.getApiService()

    fun getCurrentMovieList(requestModel: ApiRequestModel): MutableLiveData<Resource<CurrentMovieListModel>> {
        val liveData = MutableLiveData<Resource<CurrentMovieListModel>>()
        apiResponseInterface.getCurrentlyPlayingMovieList(requestModel.map)
            .enqueue(object : Callback<CurrentMovieListModel> {
                override fun onFailure(call: Call<CurrentMovieListModel>, t: Throwable) {
                    liveData.postValue(Resource.error())
                }

                override fun onResponse(
                    call: Call<CurrentMovieListModel>,
                    response: Response<CurrentMovieListModel>
                ) {
                    liveData.postValue(Resource.success(response.body()))
                }
            })
        return liveData
    }

    fun getPopularMovieList(
        requestModel: ApiRequestModel,
        popularMovieLivaData: MutableLiveData<Resource<PopularMovieListModel>>
    ): MutableLiveData<Resource<PopularMovieListModel>> {
        apiResponseInterface.getPopularMovieList(requestModel.map)
            .enqueue(object : Callback<PopularMovieListModel> {
                override fun onFailure(call: Call<PopularMovieListModel>, t: Throwable) {
                    popularMovieLivaData.postValue(Resource.error())
                }

                override fun onResponse(
                    call: Call<PopularMovieListModel>,
                    response: Response<PopularMovieListModel>
                ) {
                    popularMovieLivaData.postValue(Resource.success(response.body()))
                }
            })
        return popularMovieLivaData
    }

    fun getMovieDetails(id: String?, requestModel: ApiRequestModel):
            MutableLiveData<Resource<MovieDetailModel>> {
        val liveData = MutableLiveData<Resource<MovieDetailModel>>()
        apiResponseInterface.getMovieDetails(id, requestModel.map)
            .enqueue(object : Callback<MovieDetailModel> {
                override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
                    liveData.postValue(Resource.error())
                }

                override fun onResponse(
                    call: Call<MovieDetailModel>,
                    response: Response<MovieDetailModel>
                ) {
                    liveData.postValue(Resource.success(response.body()))
                }
            })
        return liveData
    }

    fun getImageConfiguration(requestModel: ApiRequestModel): MutableLiveData<Resource<ConfigurationModel>> {
        val liveData = MutableLiveData<Resource<ConfigurationModel>>()
        apiResponseInterface.getImageConfiguration(requestModel.map)
            .enqueue(object : Callback<ConfigurationModel> {
                override fun onFailure(call: Call<ConfigurationModel>, t: Throwable) {
                    liveData.postValue(Resource.error())
                }

                override fun onResponse(
                    call: Call<ConfigurationModel>,
                    response: Response<ConfigurationModel>
                ) {
                    liveData.postValue(Resource.success(response.body()))
                }
            })
        return liveData
    }

}