package com.example.insta

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

class NewsDetailActivity : AppCompatActivity() {

    private val tvNewsDetail: TextView? = null
    private var nickname: TextView? = null
    private var likes: TextView? = null
    private var title: TextView? = null
    private var comments: TextView? = null
    private var postData: TextView? = null
    private var ava: ImageView? = null
    private var postPhoto: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)




        nickname = findViewById<View>(R.id.tvDate)
        likes = findViewById<View>(R.id.tvViews)
        title = findViewById(R.id.tvTitle)
        comments = findViewById<View>(R.id.tvComments)
        postData = findViewById<View>(R.id.postData)
        ava = findViewById<View>(R.id.imageView3)
        postPhoto = findViewById<View>(R.id.imageButton)

        val selectedNews = intent.getParcelableExtra<Parcelable>("news") as News


        nickname!!.text = Html.fromHtml(selectedNews.author)
        likes!!.text = selectedNews.viewsCount
        title!!.text = Html.fromHtml(selectedNews.title)
        comments!!.text = selectedNews.commentsCount
        postData!!.text = selectedNews.postData

        Picasso.get().load(selectedNews.ava).into(ava)
        Picasso.get().load(selectedNews.photoLink).into(postPhoto)


    }
}
