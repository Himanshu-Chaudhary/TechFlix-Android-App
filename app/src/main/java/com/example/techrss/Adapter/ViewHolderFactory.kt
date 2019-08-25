package com.example.techrss.Adapter

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import com.example.techrss.Models.Item

class ViewHolderFactory(val resources : Resources, val adapter : NewsAdapter){

    fun bindViewHolder(viewHolder :RecyclerView.ViewHolder, index : Int){
        if (viewHolder is customViewHolder)
            viewHolder.bindData(index,resources,adapter)
    }

}