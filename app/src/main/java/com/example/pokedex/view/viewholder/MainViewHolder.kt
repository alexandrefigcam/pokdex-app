package com.example.pokedex.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.API.model.PokeModel
import com.example.pokedex.R
import com.squareup.picasso.Picasso

class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {





    private fun buildUrl(id:String):String{
        return "https://pokeres.bastionbot.org/images/pokemon/${id}.png"
    }


     fun bind(model: PokeModel){

         val poke_image = itemView.findViewById<ImageView>(R.id.image_poke)
         val name_poke = itemView.findViewById<TextView>(R.id.poke_name)

         name_poke.text = model.name



         Picasso.with(itemView.context).load(buildUrl(model.id.toString())).into(poke_image)
     }



}