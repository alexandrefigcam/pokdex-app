package com.example.pokedex.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.API.component.DaggerPokeApiComponent
import com.example.pokedex.API.listener.PokeApiListener
import com.example.pokedex.API.model.PokeModel
import com.example.pokedex.API.repository.PokeApiRepository
import com.example.pokedex.local.repository.PokeRepository
import dagger.android.AndroidInjection.inject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainViewModel(context: Context):ViewModel() {


    @Inject
    lateinit var mRepository: PokeApiRepository

    @Inject
    lateinit var mLocalRepository: PokeRepository



    init {
        DaggerPokeApiComponent.create().inject(this)
    }

    private var mListPokeIds = MutableLiveData<List<String>>()
    var mid: LiveData<List<String>> = mListPokeIds

    private var mListPoke = MutableLiveData<List<PokeModel>>()
    var mlistpokes:LiveData<List<PokeModel>> = mListPoke

    private var mFilteredList = MutableLiveData<List<PokeModel>>()
    var mfilteredlist:LiveData<List<PokeModel>> = mFilteredList

    private var mErrorMessage = MutableLiveData<String>()
    var merrormessage:LiveData<String> = mErrorMessage

    private var mLoading = MutableLiveData<Boolean>()
    var mloading:LiveData<Boolean> = mLoading

    private lateinit var mCouroutineJob:Job




    //fun fetchIdsPoke(){
        //var aux: MutableList<String> = arrayListOf()
        //CoroutineScope(Dispatchers.Default).launch {
            //for ( j in 1..100){
               // aux.add(j.toString())
           // }
            //mListPokeIds.postValue(aux)
        //}

    //}
    fun fetchView(){
        GlobalScope.launch{
            delay(7000L)
            mLoading.postValue(false)
        }
        mLoading.value = true
    }

    fun fetchPokeNames(){


        val aux_name:MutableList<PokeModel> = arrayListOf()
        val job = CoroutineScope(Dispatchers.IO + Job()).launch {
            pokeFlow().collect {value ->
                mListPoke.postValue(value)
            }
        }
        mCouroutineJob = job




    }

    suspend fun pokeFlow() = flow{
        val aux_poke: MutableList<PokeModel> = arrayListOf()
        for(k in 1..700){
            mRepository.getPokemonNames(k.toString(), object:PokeApiListener{
                override fun onSucces(model: PokeModel) {
                    aux_poke.add(model)
                    mLocalRepository.insertPokemon(model)
                }

                override fun onFailure(str: String) {

                }

            })
        }
        emit(aux_poke)
    }

    fun refresh(){
        CoroutineScope(Dispatchers.Default).launch {
            mLocalRepository.refreshDataBase()
        }

    }

    fun searchForPokemon(query:String){

            CoroutineScope(Dispatchers.IO + Job()).launch {
                    mLocalRepository.searchForPke(query) {
                        handleDataChanged(it)
                    }

            }

    }

    private fun handleDataChanged(list:MutableList<PokeModel>){
        mFilteredList.postValue(list)
    }




}