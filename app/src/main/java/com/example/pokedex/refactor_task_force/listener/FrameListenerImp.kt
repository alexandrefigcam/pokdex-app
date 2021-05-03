package com.example.pokedex.refactor_task_force.listener

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import com.example.pokedex.refactor_task_force.view.PokeInfoActivity
import com.example.pokedex.refactor_task_force.view.PokeListActivity

class FrameListenerImp(
        val mActivity:PokeListActivity,
        val mContext:Context
):FrameListener{


    override fun onClick(model:PokeModelObject, view: View) {
        val bundle = Bundle()
        val intent = Intent(view.context, PokeInfoActivity::class.java)
        bundle.putString("id", model.id.toString())
        bundle.putString("name", model.name)
        intent.putExtras(bundle)


        view.context.startActivity(intent)



    }
}