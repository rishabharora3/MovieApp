package com.personal.movieapp.network

import com.personal.movieapp.data.ConfigurationModel
import com.personal.movieapp.data.CurrentMovieListModel
import com.personal.movieapp.data.MovieDetailModel
import com.personal.movieapp.data.PopularMovieListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiResponseInterface {

    @GET("movie/now_playing")
    fun getCurrentlyPlayingMovieList(@QueryMap map: Map<String, String>): Call<CurrentMovieListModel>

    @GET("movie/popular")
    fun getPopularMovieList(@QueryMap map: Map<String, String>): Call<PopularMovieListModel>

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") id: String?,
        @QueryMap map: Map<String, String>
    ): Call<MovieDetailModel>

    @GET("configuration")
    fun getImageConfiguration(@QueryMap map: Map<String, String>): Call<ConfigurationModel>

}