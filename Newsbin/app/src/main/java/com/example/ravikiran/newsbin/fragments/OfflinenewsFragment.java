package com.example.ravikiran.newsbin.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.activities.FullNewsOfflineActivity;
import com.example.ravikiran.newsbin.adapters.ListviewAdapter;
import com.example.ravikiran.newsbin.classes.NewsDetails;
import com.example.ravikiran.newsbin.classes.NewsStorage;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfflinenewsFragment extends Fragment {

    ListView lv;
    ArrayList<NewsDetails> allOfflineNews;
    Context context;
    SwipeRefreshLayout swipeTpRefresh;
    Button getOfflineNews;


    public OfflinenewsFragment() {
        // Required empty public constructor
    }

    public static OfflinenewsFragment getInstance(){
        OfflinenewsFragment fragment = new OfflinenewsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_offlinenews, container, false);
        context=getActivity();
        lv=(ListView)v.findViewById(R.id.listView2);
        swipeTpRefresh=(SwipeRefreshLayout)v.findViewById(R.id.swipe_to_refresh2);
        swipeTpRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showList();
                swipeTpRefresh.setRefreshing(false);

            }
        });
        showList();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsDetails selectedDetails=(NewsDetails)parent.getItemAtPosition(position);
                Intent i=new Intent(getActivity(), FullNewsOfflineActivity.class);
                i.putExtra("title",selectedDetails.getNewsTitle());
                i.putExtra("source",selectedDetails.getSource());
                i.putExtra("author",selectedDetails.getAuthor());
                i.putExtra("date",selectedDetails.getDate());
                i.putExtra("image",selectedDetails.bitmapToByte(selectedDetails.getImageBitmap()));
                i.putExtra("description",selectedDetails.getDescription());
                i.putExtra("fullStoryUrl",selectedDetails.getFullStoryUrl());
                startActivity(i);
            }
        });

        return v;
    }

    public void showList(){
        allOfflineNews=new ArrayList<NewsDetails>();
        allOfflineNews.clear();
        NewsStorage showAll=new NewsStorage(context,null,null,1);
        Cursor c=showAll.returnFullTableCursor();

        if(c!=null){

            if(c.moveToFirst()){

                do{
                    NewsDetails newsDetails=new NewsDetails();
                    newsDetails.setNewsTitle(c.getString(0));
                    newsDetails.setSource(c.getString(1));
                    newsDetails.setAuthor(c.getString(2));
                    newsDetails.setDate(c.getString(3));
                    newsDetails.setImageUrl(c.getString(4));
                    newsDetails.setDescription(c.getString(5));
                    newsDetails.setImageBitmap(newsDetails.byteToBitmap(c.getBlob(6)));
                    newsDetails.setFullStoryUrl(c.getString(7));
                    allOfflineNews.add(newsDetails);


                }while(c.moveToNext());
            }
        }

        c.close();
        if(allOfflineNews.size()!=0){
            ListviewAdapter lva=new ListviewAdapter(allOfflineNews,context);
            lv.setAdapter(lva);
        }

        }
    }



