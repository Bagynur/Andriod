package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class    NewsDetailActivity extends AppCompatActivity {

    private TextView tvNewsDetail;
    private TextView nickname;
    private TextView likes;
    private TextView title;
    private TextView comments;
    private TextView postData;
    private ImageView ava;
    private ImageView postPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);




        nickname = (TextView)findViewById(R.id.tvDate);
        likes = (TextView)findViewById(R.id.tvViews);
        title = findViewById(R.id.tvTitle);
        comments = (TextView)findViewById(R.id.tvComments);
        postData =(TextView) findViewById(R.id.postData);
        ava = (ImageView) findViewById(R.id.imageView3);
        postPhoto =(ImageView) findViewById(R.id.imageButton);

        News selectedNews = (News) getIntent().getParcelableExtra("news");


        nickname.setText(Html.fromHtml(selectedNews.getAuthor()));
        likes.setText(selectedNews.getViewsCount());
        title.setText(Html.fromHtml(selectedNews.getTitle()));
        comments.setText(selectedNews.getCommentsCount());
        postData.setText(selectedNews.getPostData());

        Picasso.get().load(selectedNews.getAva()).into(ava);
        Picasso.get().load(selectedNews.getPhotoLink()).into(postPhoto);


    }
}
