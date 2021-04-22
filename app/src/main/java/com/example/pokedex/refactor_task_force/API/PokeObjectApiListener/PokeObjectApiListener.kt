package com.example.pokedex.refactor_task_force.API.PokeObjectApiListener

import com.example.pokedex.refactor_task_force.model.PokeModelObject

interface PokeObjectApiListener {


    fun onFailure(str:String)


    fun onSucces(model:PokeModelObject)
}