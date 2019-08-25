package com.example.techrss.AdapterManager

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import com.example.techrss.Models.Channel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.example.techrss.Models.Item
import com.example.techrss.Models.PodcastSupplier
import com.example.techrss.Models.RssData
import com.example.techrss.Utils.NewsServices
import com.example.techrss.Utils.RetrofirClientInstance
import org.simpleframework.xml.core.Persister
import java.io.File
import java.io.FileInputStream


interface AsyncDelegate {
    fun asynCompleteTask()
}
class AdapterManager(val resource : Resources){
    val retrofit : NewsServices
   val retrofitPodcast : NewsServices
    val adapterData: ArrayList<Item>
    val adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>
    var disposable: Disposable? = null

    init {
        adapterData = ArrayList()
        adapter = NewsAdapter(adapterData, resource)
        retrofit = RetrofirClientInstance.createInstance()
        retrofitPodcast = RetrofirClientInstance.createXMLInstance()
        getData()
        getPodcastRss()

        val tech_podcast = "http://feeds.feedburner.com/iTunesPodcastTTTechnology"
    }

    public fun getData(){
        val newsUrl = "https://api.rss2json.com/v1/api.json?" +
                "rss_url=http%3A%2F%2Fwww.theverge.com%2Fandroid%2Frss%2Findex.xml&" +
                "api_key=nn8hy1idpciwxxebmqhqx7d2uromlmh29r25yupo"
        var output: RssData
        disposable = retrofit.getNews(newsUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (this :: onRetrieveData,  this:: onError)
    }

    public fun getPodcastRss(){
        val podcast_url = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.npr.org%2Frss%2Fpodcast.php%3Fid%3D510298"
        val podcastPlay = ":https://play.podtrac.com/npr-510298/edge1.pod.npr.org/anon.npr-podcasts/podcast/npr/ted/2019/08/20190822_ted_believerspod-8991ee02-aed2-4399-8803-793dab4a3ad8.mp3?awCollectionId=510298&amp;awEpisodeId=753457020&amp;orgId=1&amp;d=3078&amp;p=510298&amp;story=753457020&amp;t=podcast&amp;e=753457020&amp;size=49131199&amp;ft=pod&amp;f=510298"
        disposable = retrofit.getNews(podcast_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (this :: onRetrievePodcastRss,  this:: onError)

    }

    private fun onRetrievePodcastRss(data: RssData){
        val a = data
    }

    public fun getPodcastLinks(){
        val podcastUrl = "https://itunes.apple.com/search?media=podcast&term=technology"
        disposable = retrofit.getSearchResults(podcastUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (this :: onRetrievePodcast,  this:: onError)
    }


    fun onRetrievePodcast(data : PodcastSupplier){
        var a = data
        var b = data.results.forEach{x-> x.feedUrl}
        var c = data.results.map{x-> x.feedUrl}
        var d = 0
    }

    fun onRetrieveData (data : RssData){
        adapterData.clear()
        adapterData.addAll(data.items)
        adapter.notifyDataSetChanged()
    }

    fun onDestroy(){
        disposable?.dispose()
    }

    fun onError(err : Throwable){
        var a = 1
    }
}