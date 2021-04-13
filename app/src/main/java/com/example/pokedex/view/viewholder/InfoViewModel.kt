package com.example.pokedex.view.viewholder

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.API.component.DaggerPokeApiComponent
import com.example.pokedex.local.repository.PokeRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.*
import javax.inject.Inject

class InfoViewModel(context: Context):ViewModel() {


    private var mLoading = MutableLiveData<Boolean>()
    var mloading:LiveData<Boolean> = mLoading

    @Inject
    lateinit var mRepository: PokeRepository

    init {
        DaggerPokeApiComponent.create().inject(this)
    }





    fun fetchView(){
       GlobalScope.launch {
           delay(3000L)
           mLoading.postValue(false)
       }
        mLoading.postValue(true)
        mRepository.refreshDataBase()
    }

}