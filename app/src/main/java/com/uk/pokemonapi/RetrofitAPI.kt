package com.uk.pokemonapi

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("pokemon")
    fun getAllPosts(): Call<PokemonList>
}
