package com.personal.movieapp.data

import com.google.gson.annotations.SerializedName

data class MovieDetailModel(
	@SerializedName("genres") val genres: List<Genres>,
	@SerializedName("id") val id: Int,
	@SerializedName("overview") val overview: String,
	@SerializedName("poster_path") val poster_path: String,
	@SerializedName("release_date") val release_date: String,
	@SerializedName("runtime") val runtime: Int,
	@SerializedName("title") val title: String,
	@SerializedName("vote_average") val vote_average: Double
)

data class Genres(
	@SerializedName("id") val id: Int,
	@SerializedName("name") val name: String
)
