package com.example.techrss.Utils

import com.example.techrss.Models.Channel
import com.example.techrss.Models.PodcastSupplier
import com.example.techrss.Models.RssData
import io.reactivex.Observable
import kotlinx.coroutines.timeunit.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsServices{


    @GET
    fun getNews(@Url url:String) : Observable<RssData>

    @GET
    fun getSearchResults(@Url url:String) :  Observable<PodcastSupplier>

    @GET
    fun getPodcastData(@Url url :String) : Observable<Channel>
}

object RetrofirClientInstance {
    fun createInstance(): NewsServices {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging).build()

        val retrofit =  Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://your.api.url")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(NewsServices::class.java)
    }

    fun createXMLInstance(): NewsServices {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.HEADERS

        val httpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging).build()

        val retrofit =  Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://your.api.url")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(NewsServices::class.java)
    }
}