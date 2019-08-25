package com.example.techrss.Utils

import android.app.Application
import android.content.res.Resources
import android.graphics.Bitmap
import android.widget.ImageView
import com.example.techrss.Models.PodcastRss
import com.example.techrss.R
import org.jsoup.SerializationException
import org.simpleframework.xml.core.Persister
import java.lang.Exception
import kotlin.random.Random

fun SerializeFromFile(application: Application){
    val file = application.assets.open("data")
    var parsedData: PodcastRss
    try{
        parsedData = Persister().read(PodcastRss::class.java, file)
    }
    catch (e : Exception){
        val d1 = 1
    }

}

fun LoadImage(source : String, imageView: ImageView, resource : Resources){
    fun _postExec(result : Bitmap?){
        imageView.setImageBitmap(result)
    }
    val empty = resource.getDrawable(R.drawable.ic_launcher_foreground)
    imageView.setImageDrawable(empty)
    ImageLoader().execute(source,::_postExec)
}
