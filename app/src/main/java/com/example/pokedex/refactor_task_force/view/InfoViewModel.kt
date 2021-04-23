package com.example.pokedex.refactor_task_force.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InfoViewModel: ViewModel() {


    private var mLoading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = mLoading


    fun fetchView(){
        GlobalScope.launch {
            delay(3000L)
            mLoading.postValue(false)
        }
        mLoading.value = true
    }
}