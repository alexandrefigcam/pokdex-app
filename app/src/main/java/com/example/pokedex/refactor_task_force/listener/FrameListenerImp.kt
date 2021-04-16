package com.example.pokedex.refactor_task_force.listener

import android.os.Bundle

class FrameListenerImp: FrameListener {


    override fun onClick(id: String) {
        val bundle = Bundle()
        bundle.putString("poke_id", id)
        //val intent = Intent()

        //startActivity(intent)
    }


}