package com.example.techrss

import android.os.AsyncTask
import android.view.View
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofirClientInstance {
    val retrofit : Retrofit;
    init {
        val url = "https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Fwww.theverge.com%2Fandroid%2Frss%2Findex.xml"
        retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}