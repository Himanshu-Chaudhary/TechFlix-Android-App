package com.example.techrss

import android.arch.lifecycle.ViewModelProvider
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

data class viewData(val data: Triple<String,String,String>, var viewType: Int)



class NewsAdapter(val data: ArrayList<Item>, val resource : Resources) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var expanded = -1

    init {

    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtView1 = itemView.findViewById<TextView>(R.id.textView)
        val txtView2 = itemView.findViewById<TextView>(R.id.textView2)

    }

    class BigViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtView1 = itemView.findViewById<TextView>(R.id.textView_big1)
        val txtView2 = itemView.findViewById<TextView>(R.id.textView_big2)
        val txtView3 = itemView.findViewById<TextView>(R.id.textView_big3)
        val txtView4 = itemView.findViewById<TextView>(R.id.textView_big4)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val data = data[p1]
        val listener: (View) -> Unit = {
            this.expanded = p1
            notifyDataSetChanged()
        }
        p0.itemView.setOnClickListener(listener)
        if (p0 is NewsViewHolder) {
            p0.txtView1.text = data.title
            p0.txtView2.text = Html.fromHtml(data.description,ImageGetter(resource,p0.txtView2),null)
        } else if (p0 is BigViewHolder) {
            p0.txtView1.text = data.author
            p0.txtView2.text = data.title
            p0.txtView3.text = Html.fromHtml(data.content,ImageGetter(resource,p0.txtView3),null)
            p0.txtView4.text = data.link
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val itemView: View

        return when (p1) {
            1 -> {
                itemView = LayoutInflater.from(p0.context).inflate(R.layout.blog_big, p0, false)
                BigViewHolder(itemView)
            }
            else -> {
                itemView = LayoutInflater.from(p0.context).inflate(R.layout.blog_small, p0, false)
                NewsViewHolder(itemView)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == this.expanded) return 1 else return 0

    }
}