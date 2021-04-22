package com.example.pokedex.refactor_task_force.myapp

import android.app.Application
import com.example.pokedex.refactor_task_force.module.myModules.appComponent
import com.example.pokedex.refactor_task_force.module.myModules.instance
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApp)

            modules(provideDependency())
        }
    }

    open fun provideDependency() = appComponent
}