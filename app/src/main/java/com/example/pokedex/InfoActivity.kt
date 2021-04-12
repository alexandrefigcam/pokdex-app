package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class InfoActivity : AppCompatActivity() {


    private var image_poke:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        if(supportActionBar!= null){
            supportActionBar!!.hide()
        }

        image_poke = findViewById(R.id.poke_image)

        updateData()



    }

    private fun updateData(){
        val extras = intent.extras

        if (extras != null){
            val id:String? = extras.getString("poke_id")
            val buildUrl = "https://pokeres.bastionbot.org/images/pokemon/${id}.png"

            Picasso.with(this).load(buildUrl).into(image_poke)
        }

    }
}