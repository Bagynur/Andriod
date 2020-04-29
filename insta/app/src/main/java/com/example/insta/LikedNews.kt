package com.example.insta

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import java.util.ArrayList

class LikedNews : Fragment {
    private var likedItems: List<News>
    private var recyclerView2: RecyclerView? = null
    private var adapter2: NewsListAdapter? = null
    //        private Database db2 = new Database();
    private lateinit var database:Database
    private var myContext: FragmentActivity? = null
    private var listener2: NewsListAdapter.ItemClickListener? = null


    constructor(liked: List<News>) {
        this.likedItems = liked
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener2 = object : NewsListAdapter.ItemClickListener {
            override fun itemClick(position: Int, item: News) {

                val intent = Intent(context, NewsDetailActivity::class.java)
                intent.putExtra("news", item)
                startActivity(intent)//go to next activity
            }

            override fun onClick(v: View, position: Int, item: News) {
                if (v.id == R.id.postLike) {
                    if (item.isPostLiked) {
                        Toast.makeText(context, "post UNliked!!!", Toast.LENGTH_SHORT).show()
                        item.isPostLiked = false

                        var likeCnt = item.viewsCount
                        likeCnt = likeCnt!!.replace("\\D+".toRegex(), "")
                        var likes = Integer.parseInt(likeCnt)
                        likes--



                        item.viewsCount = "$likes likes"
                        Database.likedNews.remove(item)
                        val fragment = LikedNews(Database.likedNews)
                        myContext!!.supportFragmentManager
                                .beginTransaction()//
                                .replace(R.id.likedNews, fragment)
                                .commitAllowingStateLoss()

                    }

                }
            }
        }

        recyclerView2 = getView()!!.findViewById<View>(R.id.recyclerView2) as RecyclerView
        recyclerView2!!.layoutManager = LinearLayoutManager(context)



        adapter2 = NewsListAdapter(this.likedItems, listener2)
        recyclerView2!!.adapter = adapter2


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_liked_news, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context as FragmentActivity
//        super.onAttach(context)
    }

    companion object {
        var likedItems: List<News> = ArrayList()
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */

}

fun <E> List<E>.remove(item: E) {

}