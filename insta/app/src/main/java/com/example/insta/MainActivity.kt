package com.example.insta

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast

abstract class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    abstract var buttonLike: Button
    internal var moreText: Button? = null
    abstract var button_home: Button
    abstract var button_man: Button


    private var adapter: NewsListAdapter? = null

    private var listener: NewsListAdapter.ItemClickListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<View>(R.id.likedNews) as FrameLayout
        layout.visibility = View.INVISIBLE

        buttonLike = findViewById<View>(R.id.postLike) as Button
        button_home = findViewById<View>(R.id.button_home) as Button
        button_man = findViewById<View>(R.id.button_man) as Button


        listener = object : NewsListAdapter.ItemClickListener {
            override fun itemClick(position: Int, item: News) {
                Toast.makeText(baseContext, "Your answer is correct!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
                intent.putExtra("news", item)
                startActivity(intent)

            }

            override fun onClick(v: View, position: Int, item: News) {
                if (v.id == R.id.postLike) {
                    if (item.isPostLiked) {
                        Toast.makeText(baseContext, "post UNliked!!!", Toast.LENGTH_SHORT).show()
                        item.isPostLiked = false
                        val holder2 = recyclerView!!.findViewHolderForAdapterPosition(position)
                        holder2!!.itemView.findViewById<View>(R.id.linearLayout2).background = ContextCompat.getDrawable(baseContext, R.drawable.menu2)

                        var likeCnt = item.viewsCount
                        likeCnt = likeCnt!!.replace("\\D+".toRegex(), "")
                        var likes = Integer.parseInt(likeCnt)
                        likes--
                        (recyclerView!!.findViewHolderForAdapterPosition(position)!!.itemView.findViewById<View>(R.id.tvViews) as TextView).text = "$likes likes"

                        item.viewsCount = "$likes likes"

                        Database.likedNews.remove(item)
                        Database.likedNews.remove(item)

                    } else {
                        Toast.makeText(baseContext, "post liked!!!", Toast.LENGTH_SHORT).show()
                        item.isPostLiked = true
                        val holder2 = recyclerView!!.findViewHolderForAdapterPosition(position)
                        holder2!!.itemView.findViewById<View>(R.id.linearLayout2).background = ContextCompat.getDrawable(baseContext, R.drawable.menu3)

                        var likeCnt = item.viewsCount
                        likeCnt = likeCnt!!.replace("\\D+".toRegex(), "")
                        var likes = Integer.parseInt(likeCnt)
                        likes++
                        (recyclerView!!.findViewHolderForAdapterPosition(position)!!.itemView.findViewById<View>(R.id.tvViews) as TextView).text = "$likes likes"
                        item.viewsCount = "$likes likes"
                        Database.likedNews.add(item)


                    }

                }
            }
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)



        adapter = NewsListAdapter(Database.newsGenerator(), listener)
        recyclerView!!.adapter = adapter


    }

    fun buttonLiked(target: View) {
        Toast.makeText(baseContext, "LIKED_POSTS!!!", Toast.LENGTH_SHORT).show()
        val fragment = LikedNews(Database.likedNews)
        findViewById<View>(R.id.bottom).background = ContextCompat.getDrawable(baseContext, R.drawable.menu44)
        supportFragmentManager
                .beginTransaction()//
                .replace(R.id.likedNews, fragment)
                .commitAllowingStateLoss()
        val layout = findViewById<View>(R.id.likedNews) as FrameLayout
        layout.visibility = View.VISIBLE

    }

    fun buttonHome(target: View) {
        Toast.makeText(baseContext, "HOME!!!", Toast.LENGTH_SHORT).show()
        findViewById<View>(R.id.bottom).background = ContextCompat.getDrawable(baseContext, R.drawable.menu0)
        val layout = findViewById<View>(R.id.likedNews) as FrameLayout
        layout.visibility = View.INVISIBLE
    }

    companion object {
        var db = Database()
    }
}
