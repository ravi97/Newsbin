package com.example.ravikiran.newsbin.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.classes.NewsDetails;
import com.example.ravikiran.newsbin.classes.NewsStorage;

public class FullNewsOfflineActivity extends AppCompatActivity {

    TextView title;
    TextView source;
    TextView author;
    TextView date;
    TextView description;
    TextView fullStoryUrl;
    ImageView storyImage;

    NewsDetails toBeShown;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news_offline);
        context=this;
        title=(TextView)findViewById(R.id.offlineTitle);
        source=(TextView)findViewById(R.id.offlineSource);
        author=(TextView)findViewById(R.id.offlineAuthor);
        date=(TextView)findViewById(R.id.offlineDate);
        description=(TextView)findViewById(R.id.offlineDesc);
        fullStoryUrl=(TextView)findViewById(R.id.offlineFullStory);
        storyImage=(ImageView)findViewById(R.id.offlineImageView);


        Intent i=getIntent();
        toBeShown=new NewsDetails();

        toBeShown.setNewsTitle(i.getStringExtra("title"));
        toBeShown.setSource(i.getStringExtra("source"));
        toBeShown.setAuthor(i.getStringExtra("author"));
        toBeShown.setDate(i.getStringExtra("date"));
        toBeShown.setDescription(i.getStringExtra("description"));
        toBeShown.setFullStoryUrl(i.getStringExtra("fullStoryUrl"));
        toBeShown.setImageBitmap(toBeShown.byteToBitmap(i.getByteArrayExtra("image")));


        title.setText(toBeShown.getNewsTitle());
        source.setText(toBeShown.getSource());
        author.setText(toBeShown.getAuthor());
        date.setText(toBeShown.getDate());
        description.setText(toBeShown.getDescription());
        fullStoryUrl.setText(toBeShown.getFullStoryUrl());
        storyImage.setImageBitmap(toBeShown.getImageBitmap());

    }

    public void removeAndGoBack(View v){
        NewsStorage toBeDeleted=new NewsStorage(context,null,null,1);
        toBeDeleted.deleteRow(toBeShown.getNewsTitle());
        Intent i=new Intent(context,MainActivity.class);
        startActivity(i);
    }


}
