package com.example.pokedex.local.repository

import com.example.pokedex.API.model.PokeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PokeRepository {


    fun insertPokemon(model:PokeModel){
         val ref = FirebaseDatabase.getInstance().getReference("pokemons")
         val pokeId = ref.push().key


        ref.child(pokeId.toString()).setValue(PokeModel(pokeId, model.id, model.name))



    }

    fun refreshDataBase(){
        val ref = FirebaseDatabase.getInstance().getReference("pokemons")
        ref.removeValue()
    }

    fun searchForPke(query:String, onDataChangedCallback:(MutableList<PokeModel>) -> Unit){

        val ref = FirebaseDatabase.getInstance().getReference("pokemons")

        var aux:MutableList<PokeModel> = arrayListOf()
        var aux_for_empty:MutableList<PokeModel> = arrayListOf()


        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {


                if (query.isNotEmpty()) {

                    for (h in snapshot.children) {
                        val poke = h.getValue(PokeModel::class.java)
                        if (poke?.name?.contains(query) == true) {
                            aux.add(poke)
                        }
                    }
                    onDataChangedCallback(aux)
                } else {
                    for(h in snapshot.children){
                        val poke = h.getValue(PokeModel::class.java)
                        poke?.let { aux_for_empty.add(it) }
                    }
                    onDataChangedCallback(aux_for_empty)
                }
            }

        })
    }




}