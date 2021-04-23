package com.example.pokedex.refactor_task_force.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.refactor_task_force.API.PokeObjectApiListener.PokeObjectApiListener
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import com.example.pokedex.refactor_task_force.repository.PokeRepositoryImp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokeListViewModel(
    val mRepository: PokeRepositoryImp
): ViewModel() {

    private var mListPokeIds = MutableLiveData<List<String>>()
    var mid: LiveData<List<String>> = mListPokeIds

    private var mListPoke = MutableLiveData<List<PokeModelObject>>()
    var mlistpokes: LiveData<List<PokeModelObject>> = mListPoke

    private var mFilteredList = MutableLiveData<List<PokeModelObject>>()
    var mfilteredlist: LiveData<List<PokeModelObject>> = mFilteredList

    private var mErrorMessage = MutableLiveData<String>()
    var merrormessage: LiveData<String> = mErrorMessage

    private var mLoading = MutableLiveData<Boolean>()
    var mloading: LiveData<Boolean> = mLoading




    fun fetchView(){
        GlobalScope.launch{
            delay(7000L)
            mLoading.postValue(false)
        }
        mLoading.value = true

    }

    fun fetchPokeNames(){

        val aux_name:MutableList<PokeModelObject> = arrayListOf()
        val job = CoroutineScope(Dispatchers.IO + Job()).launch {
            for(k in 1..700){
                mRepository.getPokemonNames(k.toString(), object: PokeObjectApiListener {
                    override fun onSucces(model: PokeModelObject) {
                        aux_name.add(model)
                        mRepository.insertPokemon(model)
                        mListPoke.postValue(aux_name)
                    }

                    override fun onFailure(str: String) {

                    }

                })
            }
        }





    }

    suspend fun pokeFlow() = flow{
        val aux_poke: MutableList<PokeModelObject> = arrayListOf()

        emit(aux_poke)
    }

    fun refresh(){

        CoroutineScope(Dispatchers.IO).launch{
            mRepository.refreshDataBase()

        }




    }

    fun searchForPokemon(query:String){

        var searchJob =  CoroutineScope(Dispatchers.IO + Job()).launch {
            mRepository.searchForPke(query) {
                handleDataChanged(it)
            }

        }


    }

    private fun handleDataChanged(list:MutableList<PokeModelObject>){
        mFilteredList.postValue(list)
    }


    private fun handleExistingData(list: MutableList<PokeModelObject>){
        mListPoke.postValue(list)
    }



}