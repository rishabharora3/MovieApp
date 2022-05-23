package com.personal.movieapp.network

import com.personal.movieapp.BuildConfig
import com.personal.movieapp.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {

    fun getApiService(): ApiResponseInterface {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getLoggingClientBuilder().build())
            .build()
            .create(ApiResponseInterface::class.java)
    }

    private fun getLoggingClientBuilder(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            // development build
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        httpClient.addInterceptor(logging)
        return httpClient
    }

}