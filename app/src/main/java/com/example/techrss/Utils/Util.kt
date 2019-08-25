package com.example.techrss.Utils

import android.app.Application
import com.example.techrss.Models.PodcastRss
import org.jsoup.SerializationException
import org.simpleframework.xml.core.Persister
import java.lang.Exception

fun SerializeFromFile(application: Application){
    val file = application.assets.open("data")
    var parsedData: PodcastRss
    try{
        parsedData = Persister().read(PodcastRss::class.java, file)
    }
    catch (e : Exception){

    }

}