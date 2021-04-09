package com.example.pokedex.API.component

import com.example.pokedex.API.module.ApiModule
import com.example.pokedex.API.repository.PokeApiRepository
import com.example.pokedex.viewmodel.MainViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface PokeApiComponent {

    fun inject(viewModel:MainViewModel)

    fun inject(repository: PokeApiRepository)
}