package com.example.pokedex.API.listener

import com.example.pokedex.API.model.PokeModel

interface PokeApiListener {


    fun onSucces(model:PokeModel)

    fun onFailure(str:String)
}