package com.personal.movieapp.data

import com.google.gson.annotations.SerializedName

data class PopularMovieListModel(
	@SerializedName("page") val page: Int? = null,
	@SerializedName("results") val results: List<PopularMovie>? = null,
	@SerializedName("total_pages") val total_pages: Int? = null,
	@SerializedName("total_results") val total_results: Int? = null
)

data class PopularMovie(
	@SerializedName("id") val id: Int,
	@SerializedName("poster_path") val poster_path: String? = null,
	@SerializedName("release_date") val release_date: String? = null,
	@SerializedName("title") val title: String? = null,
	@SerializedName("vote_average") val vote_average: Double? = null,
	@SerializedName("runtime") val runtime: Int? = null
)