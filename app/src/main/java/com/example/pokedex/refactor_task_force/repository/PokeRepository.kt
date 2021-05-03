package com.example.pokedex.refactor_task_force.repository


import com.example.pokedex.refactor_task_force.API.PokeObjectApiListener.PokeObjectApiListener
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import kotlinx.coroutines.flow.Flow

interface PokeRepository {

    suspend fun getPokemonNamesFlow():Flow<PokeModelObject?>


    suspend fun getAllPokemons():Flow<MutableList<PokeModelObject>>

    //Captura pokemons através da API
   // suspend fun getPokemonNames(id:String, listener: PokeObjectApiListener)

    //Inserer pokemons no firebase
     suspend fun insertPokemon(model: PokeModelObject)




    //Reseta firebase
    fun refreshDataBase()

    //Procura por pokemon
    fun searchForPke(query:String, onDataChangedCallback:(MutableList<PokeModelObject>) -> Unit)




}