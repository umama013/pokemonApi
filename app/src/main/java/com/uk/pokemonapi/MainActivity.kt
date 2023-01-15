package com.uk.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.uk.pokemonapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://pokeapi.co/api/v2/"
    lateinit var mainBinding: ActivityMainBinding
    var postlist = ArrayList<posts>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        showposts()
        setContentView(view)
    }
    fun showposts()
    {
        val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitApi: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call: Call<List<posts>> = retrofitApi.getAllposts()
        call.enqueue(object : Callback<List<posts>> {
            override fun onResponse(call: Call<List<posts>>, response: Response<List<posts>>) {
                if (!response.isSuccessful) {
                    mainBinding.textViewname.text = "error"
                    mainBinding.textViewurl.text = "error"
                } else {
                    postlist =
                        response.body() as ArrayList<posts> /* = java.util.ArrayList<com.uk.pokemonapi.posts> */
                    mainBinding.textViewname.text = postlist[0].name
                    mainBinding.textViewurl.text = postlist[0].url

                }

            }


            override fun onFailure(call: Call<List<posts>>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_LONG).show()


            }
        })
    }

}
