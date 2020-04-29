package com.example.insta

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Parcelable
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

class NewsDetailActivity : AppCompatActivity() {

    private val tvNewsDetail: TextView? = null
    private lateinit var nickname: TextView
    private lateinit var likes: TextView
    private lateinit var title: TextView
    private lateinit var comments: TextView
    private lateinit var postData: TextView
    private lateinit var ava: ImageView
    private lateinit var postPhoto: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)




        nickname = findViewById(R.id.tvDate)
        likes = findViewById(R.id.tvViews)
        title = findViewById(R.id.tvTitle)
        comments = findViewById(R.id.tvComments)
        postData = findViewById(R.id.postData)
        ava = findViewById(R.id.imageView3)
        postPhoto = findViewById(R.id.imageButton)

        val selectedNews = intent.getParcelableExtra<Parcelable>("news") as News


        nickname.text = Html.fromHtml(selectedNews.author)
        likes.text = selectedNews.viewsCount
        title.text = Html.fromHtml(selectedNews.title)
        comments.text = selectedNews.commentsCount
        postData.text = selectedNews.postData

        Picasso.get().load(selectedNews.ava).into(ava)
        Picasso.get().load(selectedNews.photoLink).into(postPhoto)


    }
}