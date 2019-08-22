package com.example.techrss

import android.os.AsyncTask
import android.view.View
import io.reactivex.Observable
import kotlinx.coroutines.timeunit.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface NewsServices{
    @GET("api.json?rss_url=http%3A%2F%2Fwww.theverge.com%2Fandroid%2Frss%2Findex.xml")
    fun getNews() : Observable<RssData>
}

object RetrofirClientInstance {
    fun create(): NewsServices{
        val url = "https://api.rss2json.com/v1/"
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging).build()
        val retrofit = retrofit2.Retrofit.Builder()
                .client(httpClient)
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(NewsServices::class.java)
    }
}