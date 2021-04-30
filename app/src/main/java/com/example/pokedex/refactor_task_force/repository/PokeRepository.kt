package com.example.pokedex.refactor_task_force.repository


import com.example.pokedex.refactor_task_force.API.PokeObjectApiListener.PokeObjectApiListener
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import kotlinx.coroutines.flow.Flow

interface PokeRepository {

    suspend fun getPokemonNamesFlow(id:String):Flow<PokeModelObject?>

    //Captura pokemons através da API
   // suspend fun getPokemonNames(id:String, listener: PokeObjectApiListener)

    //Inserer pokemons no firebase
    fun insertPokemon(model: PokeModelObject)


    //Carrega pokemons inseridos no firebase
    fun loadData(): Flow<MutableList<PokeModelObject>>

    //Reseta firebase
    fun refreshDataBase()

    //Procura por pokemon
    fun searchForPke(query:String, onDataChangedCallback:(MutableList<PokeModelObject>) -> Unit)



}