package com.example.techrss.Models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.ElementList


@Root(name = "rss", strict = false)
data class PodcastRss(
        @field:Element(name = "channel")
        var channel: Channel? = null
)

@Root(name = "channel", strict = false)
data class Channel(
        var author: String? = null,
        //var category: Category? = null,
        var copyright: String? = null,
        var description: String? = null,
        var docs: String? = null,
        var explicit: String? = null,
        // var image: List<Image>? = null,
        var info: Info? = null,
        @field:ElementList(name = "item", inline = true, required = false)
        var item: List<MyItem>? = null,
        var language: String? = null,
        var lastBuildDate: String? = null,
        var link: List<String>? = null,
        var managingEditor: String? = null,
        // var owner: Owner? = null,
        var pubDate: String? = null,
        //var subtitle: Any? = null,
        @field:Element(name = "title")
        var title: String? = null,
        var webMaster: String? = null
)

data class Category(
        var text: String
)

@Root(name = "item", strict = false)
data class MyItem(
        @field:Element(name = "title")
        var title: String? = null,
        @field:Element(name = "link")
        var link: String? = null,
        @field:Element(name = "description")
        var description: String? = null
)

data class Group(
        var content: List<Content>,
        var credit: Credit,
        var thumbnail: Thumbnail
)

data class Credit(
        var text: String,
        var role: String
)

data class Thumbnail(
        var height: String,
        var url: String,
        var width: String
)

data class Content(
        var bitrate: String,
        var duration: String,
        var fileSize: String,
        var url: String
)


data class MyEnclosure(
        var length: String,
        var url: String
)

data class Guid(
        var text: String,
        var isPermaLink: String
)

data class Image(
        var url: String
)

data class ImageX(
        var href: String,
        var link: String,
        var title: String,
        var url: String
)

data class Info(
        var uri: String
)

data class Owner(
        var email: String,
        var name: String
)


