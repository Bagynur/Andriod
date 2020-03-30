package com.example.insta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class LikedNews extends Fragment {
    private RecyclerView recyclerView2;
    private NewsListAdapter adapter2;
    private Database db2 = new Database();
    private FragmentActivity myContext;
    private NewsListAdapter.ItemClickListener listener2 = null;
    public static List<News> likedItems = new ArrayList<>();


    public LikedNews() {
    }
    public LikedNews(List<News> liked) {
        this.likedItems = liked;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener2 = new NewsListAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, News item) {

                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);//go to next activity
            }

            @Override
            public void onClick(View v, int position, News item) {
                if (v.getId() == R.id.postLike) {
                    if(item.isPostLiked()){
                        Toast.makeText(getContext(), "post UNliked!!!" , Toast.LENGTH_SHORT ).show();
                        item.setPostLiked(false);

                        String likeCnt =item.getViewsCount();
                        likeCnt =  likeCnt.replaceAll("\\D+","");
                        int likes = Integer.parseInt(likeCnt);
                        likes--;



                        item.setViewsCount(likes + " likes");
                        Database.likedNews.remove(item);
                        Fragment fragment = new LikedNews(Database.likedNews);
                        myContext.getSupportFragmentManager()
                                .beginTransaction()//
                                .replace(R.id.likedNews, fragment)
                                .commitAllowingStateLoss();

                    }

                }
            }
        };

        recyclerView2 =(RecyclerView) getView().findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter2 = new NewsListAdapter(this.likedItems, listener2);
        recyclerView2.setAdapter(adapter2);


    }
    @Override
    public View onCreateView(LayoutInflater inflater,   @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_liked_news, container, false);


        return v;
    }


    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
