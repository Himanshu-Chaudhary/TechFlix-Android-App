package com.example.techrss.MediaManager

import android.content.res.Resources
import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.Button
import com.example.techrss.R

class MediaManager (val resource : Resources){
    val mediaPlayer = MediaPlayer()
    var url = ""
    var button : Button? = null

    init {
        val podcastPlay = "https://play.podtrac.com/npr-510298/edge1.pod.npr.org/anon.npr-podcasts/podcast/npr/ted/2019/08/20190822_ted_believerspod-8991ee02-aed2-4399-8803-793dab4a3ad8.mp3?awCollectionId=510298&amp;awEpisodeId=753457020&amp;orgId=1&amp;d=3078&amp;p=510298&amp;story=753457020&amp;t=podcast&amp;e=753457020&amp;size=49131199&amp;ft=pod&amp;f=510298"
        mediaPlayer.apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(podcastPlay)
            prepare()
        }
    }

    fun connectToUrl(newUrl: String, button : Button){
        if (url == newUrl) return
        connect(newUrl)
        this.button = button

    }

    fun connect(url: String){
        pauseButton()
        this.url = url
        mediaPlayer.reset()
        mediaPlayer.apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare()
        }
    }

    fun play(){
        mediaPlayer.start()
        playButton()
    }

    fun pause(){
        mediaPlayer.pause()
        button?.background = resource.getDrawable(R.drawable.ic_play_arrow_black_24dp)
    }

    fun pauseButton(){
        button?.background = resource.getDrawable(R.drawable.ic_play_arrow_black_24dp)
    }

    fun playButton(){
        button?.background = resource.getDrawable(R.drawable.ic_pause_black_24dp)
    }

    fun toggle(url : String, button : Button){
        connectToUrl(url, button)
        if (mediaPlayer.isPlaying) pause()
        else play()
    }

}