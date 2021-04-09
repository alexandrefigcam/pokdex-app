package com.example.pokedex.API.model

import com.google.gson.annotations.SerializedName

data class PokeModel(
    val idFire:String? = null,
    @SerializedName("id")
    val id:String? = null,
    @SerializedName("name")
    val name:String? = null
)