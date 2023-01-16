package com.uk.pokemonapi

import com.google.gson.annotations.SerializedName

data class PokemonList(
	@SerializedName("count")
	val count: Int,
	@SerializedName("next")
	val nextURL: String,
	@SerializedName("previous")
	val previousURL: String,
	@SerializedName("results")
	val results: List<Pokemon>
)
