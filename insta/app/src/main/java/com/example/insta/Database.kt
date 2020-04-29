package com.example.insta

import java.util.ArrayList
import java.util.Random
import java.util.Vector

class Database {
    init {
        postUrls.add("https://upload.wikimedia.org/wikipedia/commons/5/5a/Canis_lupus_265b.jpg")
        postUrls.add("https://s1.stc.all.kpcdn.net/putevoditel/projectid_103889/images/tild3863-6639-4339-b063-646433656461__20180530_gaf_uw8_491.jpg")
        postUrls.add("https://knews.kg/wp-content/uploads/2019/12/volki.jpg")
        postUrls.add("https://static.zakon.kz/uploads/posts/2020-01/pic_690/2020011510144868971_tambovskiy-volk.jpg")
        postUrls.add("https://inbusiness.kz/ru/images/original/25/images/bxYftxTH.jpg")
        postUrls.add("https://vokrugsveta.ua/wp-content/uploads/2019/01/shutterstock_1041429496.jpg")

        avaUrls.add("https://www.infox.ru/photo/91d/7b0/91d7b00a160479ade52e858f16a44c39asdasdasd5de46aaa5cec79.49647512-650x433-91d7b00a160479ade52e858f16a44c39.jpg")
        avaUrls.add("https://static.caravan.kz/image/landscape/420/502516.jpg")
        avaUrls.add("https://uralpolit.ru/assets/911113a0/images/oldsite/2013/04/124924759-pic4-452x302-83269.jpeg/680.jpeg")
        avaUrls.add("https://the-criminal.ru/wp-content/uploads/2017/09/1-18-e1506086292344.jpg")
        avaUrls.add("https://ribalych.ru/wp-content/uploads/2015/08/bandit-iz-90-x_450.jpg")
        avaUrls.add("https://lh3.googleusercontent.com/proxy/q4u-30BGX3KA87DZ35hLgUaWoxd-sGzMFPQ32bW1JLgMaL56O0Jr0ZKWAdDCMnplsp2mDxAYM0Cwk_4bEShHzPo7n9YDL4AOhQGtMuG0Eiv0ktaAn2VhN0Zkvtw")
    }

    companion object {

        fun makeRandomLogin(): String {
            val ch = "abcdefghijklmnopqrstuvwxyz"
            var login = ""
            val length = 5
            val rand = Random()

            val text = CharArray(length)
            for (i in 0 until length) {
                text[i] = ch[rand.nextInt(ch.length)]
            }
            for (i in text.indices) {
                login += text[i]
            }
            return login
        }

        fun newsGenerator(): List<News> {
            val loremIpsum = "Волк не дергается, когда псы лают. Закон волков гласит, что за любовь, свободу и товарищей можно отдать даже свою жизнь. Если волк вас любит он никогда не даст вас в обиду! Он будет обижать вас сам."
            for (i in postUrls.indices) {
                val urlPost = postUrls[i]
                val urlAva = avaUrls[i]
                val rand = (Math.random() * 1000).toInt()
                val likes = Integer.toString(rand)
                val comments = Integer.toString(rand / 2)
                val login = makeRandomLogin()
                val txt = "<b>$login</b> $loremIpsum"
                val news = News(
                        "June 2$i",
                        "<b>$login</b>",
                        txt,
                        "$likes likes",
                        "View al $comments comments",
                        urlPost,
                        urlAva
                )
                items.add(news)
            }
            return items
        }

        var postUrls = Vector<String>()
        var avaUrls = Vector<String>()
        var items: MutableList<News> = ArrayList()
        var likedNews: List<News> = ArrayList()
    }


}
