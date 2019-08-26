package com.example.techrss.Adapter

import android.content.res.Resources
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.techrss.R
import com.example.techrss.Utils.*
import org.jsoup.Jsoup
import uk.co.deanwild.flowtextview.FlowTextView
import java.util.Collections.replaceAll

interface customViewHolder{
    fun bindData(index: Int, resource : Resources, adapter : NewsAdapter)
}


class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), customViewHolder {

    val txtView1 = itemView.findViewById<TextView>(R.id.txtView_music_title)
    val txtView2 = itemView.findViewById<TextView>(R.id.textView2)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView_podcast_small)

    override fun bindData(index: Int, resource : Resources, adapter : NewsAdapter) {
        val data = adapter.data[index]
        var doc = Jsoup.parse(data.content)
        var tempHtml = Html.fromHtml(data.content)
        var source = doc.select("img").attr("src")
        var title = data.title
        val desc = Jsoup.parse(data.description).select("p")[0].wholeText()
        this.txtView1.text = title
        this.txtView2.text = desc
        val color = this.txtView1.currentTextColor
        LoadImage(source,this.imageView,resource)
        var onClickListener = onClick(index,adapter)
        this.itemView.setOnClickListener(onClickListener)
    }

    fun onClick(index : Int, adapter : NewsAdapter) : (View)-> Unit{
        val onClickFun: (View)-> Unit ={
            adapter.state.expanded = index
            adapter.notifyItemChanged(index)
        }
        return onClickFun
    }


}

class BigViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), customViewHolder {



    val txtView = itemView.findViewById<TextView>(R.id.txtView_blog_big)
    val txtView_title = itemView.findViewById<TextView>(R.id.textView_blog_big_title)



    override fun bindData(index: Int, resource : Resources,adapter : NewsAdapter) {
        val data = adapter.data[index]
        var content = data.content
        content = content.replace("<small.+/(small)*>".toRegex(), "")
        this.txtView.text = Html.fromHtml(content, ImageGetter(resource,this.txtView),null)
        this.txtView_title.text = data.title
        var onClickListener = onClick(index,adapter)
        this.itemView.setOnClickListener(onClickListener)
    }

    fun onClick(index : Int, adapter : NewsAdapter) : (View)-> Unit{
        val onClickFun: (View)-> Unit ={
            adapter.state.expanded = -1
            adapter.notifyItemChanged(index)
        }
        return onClickFun
    }
}

class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), customViewHolder{
    val txtView_title = itemView.findViewById<TextView>(R.id.txtView_musi_title)
    val txtView_button = itemView.findViewById<Button>(R.id.podcast_small_button)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView_podcast_small)

    override fun bindData(index: Int, resource : Resources,adapter : NewsAdapter) {
        val data = adapter.data[index]
        val stream_url = data.enclosure.link as String
        var image_link = data.thumbnail
        this.txtView_title.text = data.title
        LoadImage(image_link, imageView, resource)



        val onButtonClick : (View)->Unit = {
            adapter.mediaPlayer.toggle(stream_url,txtView_button)
        }
        this.txtView_button.setOnClickListener(onButtonClick)


    }


}