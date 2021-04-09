package com.example.pokedex.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.pokedex.R
import com.example.pokedex.koinModules.mainActivityModules
import com.example.pokedex.view.adapter.MainAdapter
import com.example.pokedex.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var mPokemonRecycler:RecyclerView? = null
    private var mPikachu: LottieAnimationView? = null
    private var pokeSearch:EditText? = null
    private var topView: View? = null
    private var scroll_view:ScrollView? = null
    private var load_poke:LottieAnimationView? = null
    private var pokedex_logo:ImageView? = null
    private var pikachu_container:LinearLayout? = null




    private val mMainViewModel:MainViewModel by inject()
    private val mAdapter:MainAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin(this, listOf(mainActivityModules))

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        mPokemonRecycler = findViewById(R.id.recycler_pokemons)
        mPikachu = findViewById(R.id.restart)
        pokeSearch = findViewById(R.id.searchView)
        topView = findViewById(R.id.view)
        scroll_view = findViewById(R.id.scroll)
        load_poke = findViewById(R.id.loading_pokeball)
        pokedex_logo = findViewById(R.id.logo_pokedex)
        pikachu_container = findViewById(R.id.container_pikachu)

        mMainViewModel.fetchView()

        mPokemonRecycler?.apply{
            layoutManager = GridLayoutManager(context, 2)
            adapter = mAdapter
        }



        load_poke?.setAnimation("poke_load.json")
        load_poke?.playAnimation()

        mPikachu?.setAnimation("pikachu.json")
        mPikachu?.playAnimation()










        onObserver()




        pokeSearch?.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query:String = pokeSearch?.text.toString()
                searchDataBase(query)
            }

        })
    }

    private fun setView(){



    }

    private fun searchDataBase(query:String){
        mMainViewModel.searchForPokemon(query)
    }


    override fun onResume() {
        super.onResume()
        mMainViewModel.refresh()
        mMainViewModel.fetchPokeNames()
    }








    private fun onObserver(){
        mMainViewModel.mlistpokes.observe(this, Observer {
            mAdapter.updateAdapter(it)
        })
        mMainViewModel.mfilteredlist.observe(this, Observer{
            mAdapter.updateAdapter(it)
        })
        mMainViewModel.mloading.observe(this, Observer{
            if(!it){
                pokeSearch?.visibility = View.VISIBLE
                topView?.visibility = View.VISIBLE
                scroll_view?.visibility = View.VISIBLE
                pikachu_container?.visibility = View.VISIBLE
                mPikachu?.visibility = View.VISIBLE
                load_poke?.visibility = View.GONE
                pokedex_logo?.visibility = View.GONE

            } else{
                load_poke?.visibility = View.VISIBLE
                pokedex_logo?.visibility = View.VISIBLE
            }
        })

    }

    override fun onClick(v: View?) {
        val id = v?.id

    }
}