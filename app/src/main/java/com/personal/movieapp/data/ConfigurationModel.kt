package com.personal.movieapp.data

import com.google.gson.annotations.SerializedName

data class ConfigurationModel(
	@SerializedName("images") val images: Images? = null
)

data class Images(
	@SerializedName("base_url") val base_url: String? = null,
	@SerializedName("secure_base_url") val secure_base_url: String? = null,
	@SerializedName("poster_sizes") val poster_sizes: List<String>? = null
)