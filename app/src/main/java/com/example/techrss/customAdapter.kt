package com.example.techrss

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

data class viewData(val data: Triple<String,String,String>, var viewType: Int)



class NewsAdapter(val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val data = ArrayList<viewData>()
    var expanded = -1

    init {
        data.add(viewData(Triple("aaaaaa", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("1111111", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("2222222", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("333333", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("444444", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("55555", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("66666", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("77777", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("88888", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("99999", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("aaaaaa", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("aaaaaa", "bbbbb", "Cccc"), 0))
        data.add(viewData(Triple("aaaaaa", "bbbbb", "Cccc"), 0))
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
            p0.txtView1.text = data.data.first
            p0.txtView2.text = data.data.second
        } else if (p0 is BigViewHolder) {
            p0.txtView1.text = data.data.first
            p0.txtView2.text = data.data.second
            p0.txtView3.text = data.data.third
            p0.txtView4.text = data.data.first
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