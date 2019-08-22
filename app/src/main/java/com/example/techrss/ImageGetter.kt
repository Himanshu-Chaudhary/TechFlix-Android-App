package com.example.techrss

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.AsyncTask
import android.text.Html
import android.widget.TextView
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
import kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat

class ImageGetter(val resource : Resources, val textView : TextView) : Html.ImageGetter{
    override fun getDrawable(source: String?): Drawable {
        var d = LevelListDrawable()
        val empty = resource.getDrawable(R.drawable.ic_launcher_foreground)
        d.addLevel(0,0,empty)
        d.setBounds(0,0,empty.intrinsicWidth, empty.intrinsicHeight)
        LoadImage().execute(source,d,resource,textView)
        return d
    }

    class LoadImage : AsyncTask<Any, Void, Bitmap?>() {

        lateinit var mDrawable : LevelListDrawable
        lateinit var textView: TextView
        override fun doInBackground(vararg params: Any?): Bitmap? {
            val source = params[0] as String
            mDrawable = params[1] as LevelListDrawable
            val resource = params[2] as Resources
            textView = params[3] as TextView
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
            if (result == null) return
            val d = BitmapDrawable(result)
            mDrawable.addLevel(1,1,d)
            mDrawable.setBounds(0, 0, result.width, result.height);
            mDrawable.level = 1;
            val t = textView.text
            textView.text = t
        }

    }

}