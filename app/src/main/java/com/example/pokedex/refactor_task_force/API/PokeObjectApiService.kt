package com.example.pokedex.refactor_task_force.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.pokedex.refactor_task_force.model.PokeModelObject

interface PokeObjectApiService {


    @GET("pokemon/{id}/")
    fun getPokeName(@Path("id") id:String): Call<PokeModelObject>
}