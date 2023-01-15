package com.uk.pokemonapi

import com.google.gson.annotations.SerializedName

data class posts(
    @SerializedName("name")
    val name :String,
    @SerializedName("url")
    val url : String


) {
}