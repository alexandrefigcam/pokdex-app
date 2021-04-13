package com.example.pokedex

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.example.pokedex.R.color.white
import com.example.pokedex.koinModules.mainActivityModules
import com.example.pokedex.view.MainActivity
import com.example.pokedex.view.viewholder.InfoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules

class InfoActivity : AppCompatActivity(), View.OnClickListener {


    private var image_poke:ImageView? = null
    private var image_pokball:ImageView? = null
    private var mainLayout:ConstraintLayout? = null
    private var lottie:LottieAnimationView? = null
    private var curtain:View? = null
    private var go_back:ImageView? = null


    private val mInfoViewModel: InfoViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        loadKoinModules(module { factory(override=true){InfoViewModel(context = get())} })

        if(supportActionBar!= null){
            supportActionBar!!.hide()
        }

        image_poke = findViewById(R.id.poke_image)
        image_pokball = findViewById(R.id.pokeball_back)
        mainLayout = findViewById(R.id.main)
        lottie = findViewById(R.id.swablu)
        curtain = findViewById(R.id.loading_curtain)
        go_back = findViewById(R.id.back_icon)


        lottie?.setAnimation("swablu.json")
        lottie?.playAnimation()



        mInfoViewModel.fetchView()
        onObserve()
        updateData()
        onListener()





    }



    private fun onListener(){
        go_back?.setOnClickListener(this)
    }

    @SuppressLint("NewApi")
    private fun onObserve(){
        mInfoViewModel.mloading.observe(this, Observer {
            if(!it){
                image_pokball?.visibility = View.VISIBLE
                image_poke?.visibility = View.VISIBLE
                go_back?.visibility = View.VISIBLE
                loading_curtain?.visibility = View.GONE
                lottie?.visibility = View.GONE

            } else {
                lottie?.visibility = View.VISIBLE
                loading_curtain?.visibility = View.VISIBLE
            }
        })

    }

    private fun updateData(){
        val extras = intent.extras

        if (extras != null){
            val id:String? = extras.getString("poke_id")
            val buildUrl = "https://pokeres.bastionbot.org/images/pokemon/${id}.png"

            Picasso.with(this).load(buildUrl).into(image_poke)
        }

    }

    override fun onClick(v: View?) {
        val id = v?.id
        if(id == R.id.back_icon){
            finish()

        }
    }
}