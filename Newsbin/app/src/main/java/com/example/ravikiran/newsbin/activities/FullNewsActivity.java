package com.example.ravikiran.newsbin.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.classes.NewsDetails;
import com.example.ravikiran.newsbin.classes.NewsStorage;
import com.example.ravikiran.newsbin.classes.VolleySingleton;

public class FullNewsActivity extends AppCompatActivity {

    NewsDetails fullDetails;
    TextView newsTitle;
    TextView newsSource;
    TextView newsAuthor;
    TextView newsDate;
    ImageView newsImage;
    TextView newsDescription;
    TextView newsUrl;
    CheckBox saveOffline;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        context=this;

        Intent i=getIntent();

        fullDetails=new NewsDetails();
        fullDetails.setNewsTitle(i.getStringExtra("newsTitle"));
        fullDetails.setSource(i.getStringExtra("source"));
        fullDetails.setAuthor(i.getStringExtra("author"));
        fullDetails.setDate(i.getStringExtra("date"));
        fullDetails.setImageUrl(i.getStringExtra("imageUrl"));
        fullDetails.setDescription(i.getStringExtra("description"));
        fullDetails.setFullStoryUrl(i.getStringExtra("fullStoryUrl"));

        newsTitle=(TextView)findViewById(R.id.textView9);
        newsSource=(TextView)findViewById(R.id.textView10);
        newsAuthor=(TextView)findViewById(R.id.textView11);
        newsDate=(TextView)findViewById(R.id.textView12);
        newsImage=(ImageView)findViewById(R.id.newsImage);
        newsDescription=(TextView)findViewById(R.id.newsDescription);
        newsUrl=(TextView)findViewById(R.id.newsUrl);
        saveOffline=(CheckBox)findViewById(R.id.checkBox6);
        setValues();





    }

    public void setValues(){
        newsTitle.setText(fullDetails.getNewsTitle());
        newsSource.setText("source : "+fullDetails.getSource());
        newsAuthor.setText("author : "+fullDetails.getAuthor());
        newsDate.setText("date : "+fullDetails.getDate());
        newsDescription.setText(fullDetails.getDescription());
        newsUrl.setText("for full news : "+fullDetails.getFullStoryUrl());
        ImageLoader imageLoader= VolleySingleton.getInstance().getImageLoader();
        imageLoader.get(fullDetails.getImageUrl(),ImageLoader.getImageListener(newsImage,R.drawable.image_default,R.drawable.image_default));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(saveOffline.isChecked()){
            NewsStorage addNews=new NewsStorage(this,null,null,1);
            ImageLoader imageLoader= VolleySingleton.getInstance().getImageLoader();
            imageLoader.get(fullDetails.getImageUrl(),new ImageLoader.ImageListener(){

                @Override
                public void onErrorResponse(VolleyError error) {

                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    fullDetails.setImageBitmap(response.getBitmap());

                }
            });

            addNews.addRow(fullDetails);

        }
    }


}
