package com.example.pokedex.API.repository

import android.content.Context
import com.example.pokedex.API.PokeApiService
import com.example.pokedex.API.component.DaggerPokeApiComponent
import com.example.pokedex.API.listener.PokeApiListener
import com.example.pokedex.API.model.PokeModel
import com.google.gson.Gson
import retrofit2.Call
import javax.inject.Inject

import retrofit2.Callback
import retrofit2.Response

class PokeApiRepository() {




    @Inject
    lateinit var PokeApi:PokeApiService

    init {
      DaggerPokeApiComponent.create().inject(this)
    }

    suspend fun getPokemonNames(id:String, listener:PokeApiListener){

        val call = PokeApi.getPokeName(id)

        call.enqueue(object: Callback<PokeModel> {
            override fun onFailure(call: Call<PokeModel>, t: Throwable) {
                listener.onFailure(t.toString())
            }

            override fun onResponse(call: Call<PokeModel>, response: Response<PokeModel>) {
                if(response.code() != 200){
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else{
                    response.body()?.let {
                        listener.onSucces(it)
                    }
                }
            }

        })
    }
}