package com.example.techrss.Utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.FileNotFoundException
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class ImageLoader : AsyncTask<Any, Void, Bitmap?>() {


    lateinit var postExec : (result: Bitmap?) -> Any
    override fun doInBackground(vararg params: Any?): Bitmap? {
        val source = params[0] as String
        postExec = params[1] as (Bitmap?) -> Any
        try{
            val url = URL(source).openStream()
            return BitmapFactory.decodeStream(url)
        }
        catch ( e: FileNotFoundException) {
            e.printStackTrace()
        } catch ( e : MalformedURLException) {
            e.printStackTrace()
        } catch ( e : IOException) {
            e.printStackTrace()
        }
        return null
    }

     override fun onPostExecute(result: Bitmap?) {
         super.onPostExecute(result)
         this.postExec(result)
    }

}