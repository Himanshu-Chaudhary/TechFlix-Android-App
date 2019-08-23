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

    var mDrawable = LevelListDrawable()
    override fun getDrawable(source: String?): Drawable {
        val empty = resource.getDrawable(R.drawable.ic_launcher_foreground)
        mDrawable.addLevel(0,0,empty)
        mDrawable.setBounds(0,0,empty.intrinsicWidth, empty.intrinsicHeight)
        ImageLoader().execute(source,:: onPostExecute)
        return mDrawable
    }

    fun onPostExecute(result: Bitmap?) {
        if (result == null) return
        val d = BitmapDrawable(result)
        mDrawable.addLevel(1,1,d)
        mDrawable.setBounds(0, 0, result.width, result.height);
        mDrawable.level = 1;
        val t = textView.text
        textView.text = t
    }




    }