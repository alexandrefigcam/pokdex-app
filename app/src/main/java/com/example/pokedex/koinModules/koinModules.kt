package com.example.pokedex.koinModules

import com.example.pokedex.API.repository.PokeApiRepository
import com.example.pokedex.view.adapter.MainAdapter
import com.example.pokedex.viewmodel.MainViewModel
import org.koin.dsl.module.module

val mainActivityModules = module{
    factory{MainViewModel(context = get())}
    factory{MainAdapter(context= get())}
}

