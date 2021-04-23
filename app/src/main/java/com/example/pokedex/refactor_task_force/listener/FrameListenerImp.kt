package com.example.pokedex.refactor_task_force.listener

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.pokedex.refactor_task_force.view.PokeInfoActivity

class FrameListenerImp(
        val mContext: Context
):FrameListener{


    override fun onClick(id: String) {
        val bundle = Bundle()
        bundle.putString("poke_id", id)
        //val intent = Intent()

        val intent = Intent(mContext,PokeInfoActivity::class.java )
        intent.putExtras(bundle)
        mContext.startActivity(intent)

    }
}