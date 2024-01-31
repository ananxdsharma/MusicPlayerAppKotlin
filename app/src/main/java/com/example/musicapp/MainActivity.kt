package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.ActivityMainBinding
//import com.example.musicapp.data-binding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchIcon: ImageView
    private lateinit var searchEditText: EditText
    private lateinit var searchResultRecyclerView : RecyclerView
    private lateinit var searchResultAdapter: SearchResultRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        searchResultRecyclerView = binding.searchResultRecyclerView
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)


        searchIcon = binding.searchIcon
        searchEditText = binding.searchEditText

        searchIcon.setOnClickListener {
            Log.i("mssg","the search button is clicked")
            val retrofitData = retrofitBuilder.getData(binding.searchEditText.text.toString())
            retrofitData.enqueue(object : Callback<MusicSearch?>{
                override fun onResponse(
                    call: Call<MusicSearch?>,
                    response: Response<MusicSearch?>
                ) {
                    if (response.isSuccessful) {
                        val searchResponse = response.body()
                        val musicSearchList = searchResponse?.data!!
                        println(musicSearchList)

                        searchResultAdapter = SearchResultRecyclerAdapter(this@MainActivity, musicSearchList)
                        searchResultRecyclerView.adapter = searchResultAdapter
                        searchResultRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                    }
                    else {
                        // Handle unsuccessful response
                        Log.i("mssg","unsucceful response")
                    }
                }

                override fun onFailure(call: Call<MusicSearch?>, t: Throwable) {
                    Log.i("mssg","onFailure")
                }

            })
        }
    }
}