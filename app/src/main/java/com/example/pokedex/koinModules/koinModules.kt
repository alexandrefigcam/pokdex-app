package com.example.pokedex.koinModules

import com.example.pokedex.API.repository.PokeApiRepository
import com.example.pokedex.listener.FrameListener
import com.example.pokedex.view.adapter.MainAdapter
import com.example.pokedex.view.viewholder.InfoViewModel
import com.example.pokedex.viewmodel.MainViewModel
import org.koin.dsl.module.module

val mainActivityModules = module{
    factory{MainViewModel(context = get())}
    factory{MainAdapter(context= get())}
    factory(override = true) { InfoViewModel(context = get()) }

}




