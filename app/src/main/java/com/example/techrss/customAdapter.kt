package com.example.techrss

import android.content.res.Resources
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jsoup.Jsoup

data class viewData(val data: Triple<String,String,String>, var viewType: Int)



class NewsAdapter(val data: ArrayList<Item>, val resource : Resources) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var expanded = -1

    init {

    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtView1 = itemView.findViewById<TextView>(R.id.textView)
        val txtView2 = itemView.findViewById<TextView>(R.id.textView2)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView_small)

    }

    class BigViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtView1 = itemView.findViewById<TextView>(R.id.textView_big1)
        val txtView2 = itemView.findViewById<TextView>(R.id.textView_big2)
        val txtView3 = itemView.findViewById<TextView>(R.id.textView_big3)
        val txtView4 = itemView.findViewById<TextView>(R.id.textView_big4)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val data = data[p1]
        val expander: (View) -> Unit = {
            this.expanded = p1
            notifyDataSetChanged()
        }
        val deflater: (View) -> Unit = {
            this.expanded = -1
            notifyDataSetChanged()
        }

        p0.itemView.setOnClickListener(expander)
        if (p0 is NewsViewHolder) {
            var doc = Jsoup.parse(data.content)
            var tempHtml = Html.fromHtml(data.content)
            var source = doc.select("img").attr("src")
            var title = data.title
            val desc = Jsoup.parse(data.description).select("p")[0].wholeText()
            p0.txtView1.text = title
            p0.txtView2.text = desc
            funLoadImage(source,p0.imageView)

        } else if (p0 is BigViewHolder) {


            p0.txtView1.text = data.author
            p0.txtView2.text = data.title
            p0.txtView3.text = Html.fromHtml(data.content,ImageGetter(resource,p0.txtView3),null)
            p0.txtView4.text = data.link
            p0.itemView.setOnClickListener(deflater)
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

    fun funLoadImage(source : String, imageView: ImageView){
        fun _postExec(result : Bitmap?){
            imageView.setImageBitmap(result)
        }
        val empty = resource.getDrawable(R.drawable.ic_launcher_foreground)
        imageView.setImageDrawable(empty)
        ImageLoader().execute(source,::_postExec)
    }
}