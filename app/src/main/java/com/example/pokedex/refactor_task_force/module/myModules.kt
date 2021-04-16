package com.example.pokedex.refactor_task_force.module


import com.example.pokedex.refactor_task_force.API.PokeObjectApiService
import com.example.pokedex.refactor_task_force.constants.PokeConstants
import com.example.pokedex.refactor_task_force.repository.PokeRepositoryImp
import com.example.pokedex.refactor_task_force.view.PokeListActivity
import com.example.pokedex.refactor_task_force.view.PokeListViewModel
import com.example.pokedex.refactor_task_force.view.adapter.PokeListAdapter
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object myModules {


    val instance = module{
        viewModel{
            PokeListViewModel(
                mRepository = PokeRepositoryImp(
                   PokeApi = Retrofit.Builder()
                       .baseUrl(PokeConstants.API.BASEURL)
                       .client(OkHttpClient.Builder().build())
                       .addConverterFactory(GsonConverterFactory.create())
                       .build()
                       .create(PokeObjectApiService::class.java)
                )
            )
        }
    }

    val adapterDependency = module {
        factory { PokeListAdapter() }
    }


    val appComponent = listOf(
              instance,
        adapterDependency

    )
}