package com.example.pokedex.refactor_task_force.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.example.pokedex.R
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class PokeInfoActivity : AppCompatActivity(), View.OnClickListener {

    private var curtain: View? = null
    private var back_pokeball:ImageView? = null
    private var poke_image:ImageView? = null
    private var back_icon:ImageView? = null
    private var swablu:LottieAnimationView? = null
    private var name_field: TextView? = null

    private val mInfoViewModel: InfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_info)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        fetchIds()
        getData()

        swablu?.setAnimation("swablu.json")
        swablu?.playAnimation()

        mInfoViewModel.fetchView()
        onObserver()
        onListener()
    }

    private fun getData(){
        val extras = intent.extras
        val id = extras?.getString("poke_id")
        val name = extras?.getString("poke_name")
        name_field?.setText(name)
        Picasso.with(this).load("https://pokeres.bastionbot.org/images/pokemon/${id}.png").into(poke_image)
    }

    private fun onListener(){
        back_icon?.setOnClickListener(this)
    }

    private fun fetchIds(){
        curtain = findViewById(R.id.loading_curtain)
        back_pokeball = findViewById(R.id.pokeball_back)
        poke_image = findViewById(R.id.poke_image)
        back_icon = findViewById(R.id.back_icon)
        swablu = findViewById(R.id.swablu)
        name_field = findViewById(R.id.pke_name)
    }


    private fun onObserver(){
        mInfoViewModel.loading.observe(this, Observer{
            if(it){
                curtain?.visibility = View.VISIBLE
                swablu?.visibility = View.VISIBLE
                back_pokeball?.visibility = View.GONE
                poke_image?.visibility = View.GONE
                back_icon?.visibility = View.GONE
                name_field?.visibility= View.GONE


            } else {

                curtain?.visibility = View.GONE
                swablu?.visibility = View.GONE
                back_pokeball?.visibility = View.VISIBLE
                poke_image?.visibility = View.VISIBLE
                back_icon?.visibility = View.VISIBLE
                name_field?.visibility = View.VISIBLE

            }
        })
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if(id == R.id.back_icon){
            finish()
        }
    }
}