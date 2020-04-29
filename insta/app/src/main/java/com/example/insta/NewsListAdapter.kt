package com.example.insta

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private var newsList: List<News>? = null
    private var listener: ItemClickListener?

    constructor(newsList: List<News>, listener: ItemClickListener?) {
        this.newsList = newsList
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, null, false)
        val params = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList!![position]
        holder.tvAuthor.text = Html.fromHtml(news.author)
        holder.tvTitle.text = Html.fromHtml(news.title)
        holder.tvComments.text = news.commentsCount.toString()
        holder.tvViews.text = news.viewsCount
        Picasso.get().load(news.ava).into(holder.ava)
        Picasso.get().load(news.photoLink).into(holder.postPhoto)
        holder.postData.text = news.postData

        holder.buttonLike.setOnClickListener { v ->
            listener?.onClick(v, position, news)
        }

        holder.itemView.setOnClickListener {
            listener?.itemClick(position, news)
        }
    }

    override fun getItemCount(): Int {
        return newsList!!.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvTitle: TextView
        internal var tvAuthor: TextView
        internal var tvViews: TextView
        internal var tvComments: TextView
        internal var ava: ImageView
        internal var postPhoto: ImageView
        internal var postData: TextView

        internal var buttonLike: Button
        internal var moreText: Button? = null

        init {
            buttonLike = itemView.findViewById<View>(R.id.postLike) as Button


            ava = itemView.findViewById<View>(R.id.imageView3) as ImageView
            postPhoto = itemView.findViewById<View>(R.id.imageButton) as ImageView

            postData = itemView.findViewById(R.id.postData)

            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvAuthor = itemView.findViewById(R.id.tvDate)

            tvViews = itemView.findViewById(R.id.tvViews)
            tvComments = itemView.findViewById(R.id.tvComments)
        }


    }

    interface ItemClickListener {
        fun itemClick(position: Int, item: News)
        fun onClick(v: View, position: Int, item: News)
    }
}
