package com.example.musicapp

import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @Headers("X-RapidAPI-Key:5e159e083emshc657f0adb4d098ap1f120bjsn7ada92ac80f0",
        "X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com"
    )
    @GET ("search")
    fun getData(@Query("q") query:String): Call<MusicSearch>
}