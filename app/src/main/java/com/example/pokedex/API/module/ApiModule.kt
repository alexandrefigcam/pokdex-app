package com.example.pokedex.API.module

import com.example.pokedex.API.PokeApiService
import com.example.pokedex.API.repository.PokeApiRepository
import com.example.pokedex.local.repository.PokeRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {

    private var base_url = "https://pokeapi.co/api/v2/"
    private var httpClient = OkHttpClient.Builder()

    @Provides
    fun getPokeApi():PokeApiService{
        return Retrofit.Builder()
            .baseUrl(base_url)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

    @Provides
    fun getPokeRepository():PokeApiRepository{
        return PokeApiRepository()
    }

    @Provides
    fun getLocalRepository():PokeRepository{
        return PokeRepository()
    }
}