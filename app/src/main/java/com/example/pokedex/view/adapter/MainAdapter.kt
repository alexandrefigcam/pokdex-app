package com.example.pokedex.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.API.model.PokeModel
import com.example.pokedex.R
import com.example.pokedex.view.viewholder.MainViewHolder

class MainAdapter(context:Context):RecyclerView.Adapter<MainViewHolder>() {

    private var mList:List<PokeModel> = arrayListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_pokemons, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun updateAdapter(list:List<PokeModel>){
        mList = list
        notifyDataSetChanged()
    }




}