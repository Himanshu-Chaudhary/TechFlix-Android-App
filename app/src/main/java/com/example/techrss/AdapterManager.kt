package com.example.techrss

import android.support.v7.widget.RecyclerView

interface AsyncDelegate {
    fun asynCompleteTask()
}
class AdapterManager(val adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>){

    init {
        val Mmyadapter = adapter
    }
}