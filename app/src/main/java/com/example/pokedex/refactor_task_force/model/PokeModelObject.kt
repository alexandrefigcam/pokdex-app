package com.example.pokedex.refactor_task_force.model

import com.google.gson.annotations.SerializedName

data class PokeModelObject(
    val idFire:String? = null,
    @SerializedName("id")
    val id:String? = null,
    @SerializedName("name")
    val name:String? = null
)
