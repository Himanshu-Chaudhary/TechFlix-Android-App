package com.example.techrss

import com.google.gson.annotations.SerializedName

class RssData(
    @SerializedName("feed")
    val feed: Feed,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("status")
    val status: String
)

data class Feed(
    val author: String,
    val description: String,
    val image: String,
    val link: String,
    val title: String,
    val url: String
)

data class Item(
    val author: String,
    val categories: List<Any>,
    val content: String,
    val description: String,
    val enclosure: Enclosure,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)

class Enclosure(
)