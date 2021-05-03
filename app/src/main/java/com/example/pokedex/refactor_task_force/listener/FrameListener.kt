package com.example.pokedex.refactor_task_force.listener

import android.view.View
import com.example.pokedex.refactor_task_force.model.PokeModelObject

interface FrameListener {

        fun onClick(model: PokeModelObject, view: View)

}