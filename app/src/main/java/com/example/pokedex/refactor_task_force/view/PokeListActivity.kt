package com.example.pokedex.refactor_task_force.view

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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.pokedex.R
import com.example.pokedex.refactor_task_force.listener.FrameListener

import com.example.pokedex.refactor_task_force.view.adapter.PokeListAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PokeListActivity() : AppCompatActivity(){


    private var mPokemonRecycler: RecyclerView? = null//Pokemons' list recycler view
    private var mPikachu: LottieAnimationView? = null //Pikachu's face lottie animation
    private var pokeSearch: EditText? = null // SApp's search tool
    private var topView: View? = null //Header containing  the search tool and pikachu's animation
    private var scroll_view: ScrollView? = null //Scroll view containing the recycler view
    private var load_poke: LottieAnimationView? = null //Lottie animation for inicial loading
    private var pokedex_logo: ImageView? = null //Logo for inicial loading screen
    private var pikachu_container: LinearLayout? = null //Linear layout wich conatins the pikachu's lottie animation
    private var pokeball_deco: LinearLayout? = null

    private val mPokeListViewModel: PokeListViewModel by viewModel()
    private val mPokeListAdapter: PokeListAdapter by inject()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_list)


        if(supportActionBar!= null){
            supportActionBar!!.hide()
        }







        attachIds() // Attach all the view's ids with the global variables
        mPokeListViewModel.fetchView() //Sets the loading animation while the pokemons are being loaded
        recyclerViewConfig() //Set up recycler view







        //Starts the loading lottie animation
        load_poke?.setAnimation("poke_load.json")
        load_poke?.playAnimation()

        //Starts the pikachu lottie animation
        mPikachu?.setAnimation("pikachu.json")
        mPikachu?.playAnimation()


        mPokeListViewModel.refresh()
        mPokeListViewModel.fetchPokeNames()







        onObserver()//Observes the ViewModel's variables




        //Deals with the query for searching pokemon
        pokeSearch?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query:String = pokeSearch?.text.toString()
                searchDataBase(query)
            }

        })
    }


    private fun recyclerViewConfig(){
        mPokemonRecycler?.apply{
            layoutManager = GridLayoutManager(context, 2)
            adapter = mPokeListAdapter
        }

    }

    private fun attachIds(){

        mPokemonRecycler = findViewById(R.id.recycler_pokemons)
        mPikachu = findViewById(R.id.restart)
        pokeSearch = findViewById(R.id.searchView)
        topView = findViewById(R.id.view)
        scroll_view = findViewById(R.id.scroll)
        load_poke = findViewById(R.id.loading_pokeball)
        pokedex_logo = findViewById(R.id.logo_pokedex)
        pikachu_container = findViewById(R.id.container_pikachu)
        pokeball_deco = findViewById(R.id.poke_symball_deco)


    }

    private fun searchDataBase(query:String){
        mPokeListViewModel.searchForPokemon(query)
    }


    override fun onResume() {
        super.onResume()



    }

    override fun onPause(){
        super.onPause()

    }








    private fun onObserver(){
        mPokeListViewModel.mlistpokes.observe(this, Observer {
            mPokeListAdapter.updateAdapter(it)
        })
        mPokeListViewModel.mfilteredlist.observe(this, Observer{
            mPokeListAdapter.updateAdapter(it)
        })
        mPokeListViewModel.mloading.observe(this, Observer{
            if(!it){
                pokeSearch?.visibility = View.VISIBLE
                topView?.visibility = View.VISIBLE
                scroll_view?.visibility = View.VISIBLE
                pikachu_container?.visibility = View.VISIBLE
                mPikachu?.visibility = View.VISIBLE
                pokeball_deco?.visibility = View.VISIBLE
                load_poke?.visibility = View.GONE
                pokedex_logo?.visibility = View.GONE

            } else{
                load_poke?.visibility = View.VISIBLE
                pokedex_logo?.visibility = View.VISIBLE
            }
        })

    }


}