package com.example.pokedex.refactor_task_force.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.pokedex.R
import com.example.pokedex.refactor_task_force.listener.FrameListener

import com.example.pokedex.refactor_task_force.model.PokeModelObject
import com.squareup.picasso.Picasso

class PokeListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {


    private fun buildUrl(id:String):String{
        return "https://pokeres.bastionbot.org/images/pokemon/${id}.png"
    }


    fun bind(model:PokeModelObject, listener: FrameListener){

        val poke_image = itemView.findViewById<ImageView>(R.id.image_poke)
        val name_poke = itemView.findViewById<TextView>(R.id.poke_name)









        name_poke.text = model.name


        Picasso.with(itemView.context).load(buildUrl(model.id.toString())).into(poke_image)


        poke_image?.setOnClickListener{
            listener.onClick(model.id.toString())
        }
    }





}