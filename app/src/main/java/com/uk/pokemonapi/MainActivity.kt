package com.uk.pokemonapi

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uk.pokemonapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

	private val baseUrl = "https://pokeapi.co/api/v2/"
	lateinit var mainBinding: ActivityMainBinding
	var postlist: List<Pokemon> = mutableListOf()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		mainBinding = ActivityMainBinding.inflate(layoutInflater)
		val view = mainBinding.root
		showPosts()
		setContentView(view)
	}

	fun showPosts() {
		val retrofit = Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		val retrofitApi: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)
		val call: Call<PokemonList> = retrofitApi.getAllPosts()
		Log.d("test", "call enqueued")
		call.enqueue(object : Callback<PokemonList> {
			override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
				Log.d("test", "call response received")
				if (!response.isSuccessful) {
					mainBinding.textViewname.text = "error"
					mainBinding.textViewurl.text = "error"
				} else {
					val pokemonList = response.body() as PokemonList
					postlist = pokemonList.results
					mainBinding.textViewname.text = postlist[0].name
					mainBinding.textViewurl.text = postlist[0].url
				}
			}

			override fun onFailure(call: Call<PokemonList>, t: Throwable) {
				Log.d("test", "call failed ${t.localizedMessage}")
				t.printStackTrace()
				Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_LONG).show()
			}
		})
	}

}
