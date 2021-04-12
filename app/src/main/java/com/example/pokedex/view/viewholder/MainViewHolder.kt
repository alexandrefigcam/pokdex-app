package com.example.pokedex.view.viewholder

import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.pokedex.API.model.PokeModel
import com.example.pokedex.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {





    private fun buildUrl(id:String):String{
        return "https://pokeres.bastionbot.org/images/pokemon/${id}.png"
    }


     fun bind(model: PokeModel){

         val poke_image = itemView.findViewById<ImageView>(R.id.image_poke)
         val name_poke = itemView.findViewById<TextView>(R.id.poke_name)
         val lottie_container = itemView.findViewById<LinearLayout>(R.id.container_forLottie)
         val egg_lottie = itemView.findViewById<LottieAnimationView>(R.id.egg_ani)

         egg_lottie.setAnimation("egg_aniamtion.json")
         egg_lottie.playAnimation()





         name_poke.text = model.name





         Picasso.with(itemView.context).load(buildUrl(model.id.toString())).into(poke_image)
     }



}