package com.personal.movieapp.data

import com.google.gson.annotations.SerializedName


data class CurrentMovieListModel(
	@SerializedName("results") val results: List<CurrentMovie>? = null
)

data class CurrentMovie(
	@SerializedName("id") val id: Int,
	@SerializedName("poster_path") val poster_path: String? = null
)