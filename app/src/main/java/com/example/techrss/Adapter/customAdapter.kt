package com.example.techrss.Adapter

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.techrss.MediaManager.MediaManager
import com.example.techrss.Models.Item
import com.example.techrss.Models.ViewHolderType
import com.example.techrss.R

data class viewData(val data: Triple<String,String,String>, var viewType: Int)

enum class ViewHolderState(val state : Int){
    News_Big(0), News_small(1), Podcast_big(2), Podcast_small(3)
}

class NewsAdapter(val data: ArrayList<Item>, val resource : Resources) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val state = AdapterState()
    val mediaPlayer = MediaManager(resource)
    val viewHolderFactory : ViewHolderFactory = ViewHolderFactory(resource, this)




    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        viewHolderFactory.bindViewHolder(p0,p1)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val itemView: View

        return when (p1) {
            ViewHolderState.News_Big.state -> {
                itemView = LayoutInflater.from(p0.context).inflate(R.layout.blog_big, p0, false)
                BigViewHolder(itemView)
            }
            ViewHolderState.News_small.state -> {
                itemView = LayoutInflater.from(p0.context).inflate(R.layout.blog_small, p0, false)
                NewsViewHolder(itemView)
            }
            else -> {
                itemView = LayoutInflater.from(p0.context).inflate(R.layout.podcast_small, p0, false)
                PodcastViewHolder(itemView)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        if (data[position].itemType == ViewHolderType.NEWS){
            if (position == this.state.expanded) return ViewHolderState.News_Big.state
            return ViewHolderState.News_small.state
        }

        return ViewHolderState.Podcast_small.state

    }
}