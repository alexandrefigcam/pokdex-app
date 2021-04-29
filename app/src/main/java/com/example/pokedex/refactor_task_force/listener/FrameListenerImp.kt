package com.example.pokedex.refactor_task_force.listener

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import com.example.pokedex.refactor_task_force.view.PokeInfoActivity

class FrameListenerImp(
        val mContext: Context
):FrameListener{


    override fun onClick(model:PokeModelObject) {
        val bundle = Bundle()
        bundle.putString("poke_id", model.id.toString())
        bundle.putString("poke_name", model.name)
        //val intent = Intent()

        val intent = Intent(mContext,PokeInfoActivity::class.java )
        intent.putExtras(bundle)
        mContext.startActivity(intent)

    }
}