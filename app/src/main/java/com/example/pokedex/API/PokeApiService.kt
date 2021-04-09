package com.example.pokedex.API

import com.example.pokedex.API.model.PokeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon/{id}/")
    fun getPokeName(@Path("id") id:String): Call<PokeModel>
}