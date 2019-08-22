package com.example.techrss

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.lang.Error

interface AsyncDelegate {
    fun asynCompleteTask()
}
class AdapterManager(val resource : Resources){
    val retrofit : NewsServices
    val adapterData: ArrayList<Item>
    val adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>
    var disposable: Disposable? = null

    init {
        adapterData = ArrayList()
        adapter = NewsAdapter(adapterData,resource)
        retrofit = RetrofirClientInstance.create()
        getData()
    }

    public fun getData(){
        val rss_url= "http%3A%2F%2Fwww.theverge.com%2Fandroid%2Frss%2Findex.xml"
        var output: RssData
        disposable = retrofit.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (this :: onRetrieveData,  this:: onError)
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