package com.example.techrss.Utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.text.Html
import android.widget.TextView
import com.example.techrss.R
import android.graphics.drawable.ColorDrawable
import android.graphics.Color


class ImageGetter(val resource : Resources, val textView : TextView) : Html.ImageGetter{

    var mDrawable = LevelListDrawable()
    override fun getDrawable(source: String?): Drawable {
        val empty = resource.getDrawable(R.drawable.ic_launcher_foreground)
        mDrawable.addLevel(0,0,empty)
        mDrawable.setBounds(0,0,100, 100)
        ImageLoader().execute(source,:: onPostExecute)
        return mDrawable
    }

    fun onPostExecute(result: Bitmap?) {
        if (result == null) return
        val d = BitmapDrawable(result)
        mDrawable.addLevel(1,1,d)
        mDrawable.setBounds(0, 0, result.width/3, result.height/3);
        mDrawable.level = 1;
        val t = textView.text
        textView.text = t
    }




    }

class EmptyImageGetter : Html.ImageGetter {

    override fun getDrawable(source: String): Drawable {
        return TRANSPARENT_DRAWABLE
    }

    companion object {
        private val TRANSPARENT_DRAWABLE = ColorDrawable(Color.TRANSPARENT)
    }
}