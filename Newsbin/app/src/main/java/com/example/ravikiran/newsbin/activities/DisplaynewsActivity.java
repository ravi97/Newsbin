package com.example.ravikiran.newsbin.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.adapters.ListviewAdapter;
import com.example.ravikiran.newsbin.classes.NewsDetails;
import com.example.ravikiran.newsbin.classes.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplaynewsActivity extends AppCompatActivity {

    Boolean sports,science,business,culture,international;
    ArrayList<NewsDetails> allNews;
    ListView lv;
    SwipeRefreshLayout swipeToRefresh;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaynews);
        context=this;
        allNews=new ArrayList<NewsDetails>();
        lv=(ListView)findViewById(R.id.listView);
        swipeToRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_to_refresh);
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(sports){
                    Toast.makeText(DisplaynewsActivity.this, "refreshing", Toast.LENGTH_SHORT).show();
                    addSportsJson();
                }
                if(science){
                    Toast.makeText(DisplaynewsActivity.this, "refreshing", Toast.LENGTH_SHORT).show();
                    addScienceJson();
                }
                if(business){
                    Toast.makeText(DisplaynewsActivity.this, "refreshing", Toast.LENGTH_SHORT).show();
                    addBusinessJson();
                }
                if(culture){
                    Toast.makeText(DisplaynewsActivity.this, "refreshing", Toast.LENGTH_SHORT).show();
                    addCulturalJson();
                }
                if(international){
                    Toast.makeText(DisplaynewsActivity.this, "refreshing", Toast.LENGTH_SHORT).show();
                    addInternationalJson();
                }
                swipeToRefresh.setRefreshing(false);

            }
        });
        Intent i=getIntent();
        sports=i.getBooleanExtra("sports",false);
        science=i.getBooleanExtra("science",false);
        business=i.getBooleanExtra("business",false);
        culture=i.getBooleanExtra("culture",false);
        international=i.getBooleanExtra("international",false);
        if(sports){
            Toast.makeText(DisplaynewsActivity.this, "fetching sports news", Toast.LENGTH_SHORT).show();
            addSportsJson();
        }
        if(science){
            Toast.makeText(DisplaynewsActivity.this, "feching science news", Toast.LENGTH_SHORT).show();
            addScienceJson();
        }
        if(business){
            Toast.makeText(DisplaynewsActivity.this, "fetching business news", Toast.LENGTH_SHORT).show();
            addBusinessJson();
        }
        if(culture){
            Toast.makeText(DisplaynewsActivity.this, "fetching culture news", Toast.LENGTH_SHORT).show();
            addCulturalJson();
        }
        if(international){
            Toast.makeText(DisplaynewsActivity.this, "fetching international news", Toast.LENGTH_SHORT).show();
            addInternationalJson();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i2=new Intent(context,FullNewsActivity.class);
                i2.putExtra("author",allNews.get(position).getAuthor());
                i2.putExtra("newsTitle",allNews.get(position).getNewsTitle());
                i2.putExtra("date",allNews.get(position).getDate());
                i2.putExtra("description",allNews.get(position).getDescription());
                i2.putExtra("imageUrl",allNews.get(position).getImageUrl());
                i2.putExtra("fullStoryUrl",allNews.get(position).getFullStoryUrl());
                i2.putExtra("source",allNews.get(position).getSource());
                startActivity(i2);
            }
        });
    }

    public void addSportsJson(){

        RequestQueue queue= VolleySingleton.getInstance().getRequestQueue();

        JsonObjectRequest newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=bbcsport&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(newsRequest);

       newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

    }

    public void addScienceJson(){

        RequestQueue queue= VolleySingleton.getInstance().getRequestQueue();

        JsonObjectRequest newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=arstechnica&sortBy=latest&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(newsRequest);

         newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=engadget&sortBy=latest&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=hackernews&sortBy=latest&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=techcrunch&sortBy=latest&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=thenextweb&sortBy=latest&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=theverge&sortBy=latest&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=recode&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);


    }

    public void addBusinessJson(){

        RequestQueue queue= VolleySingleton.getInstance().getRequestQueue();

        JsonObjectRequest newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=bloomberg&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=cnbc&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=thehuffingtonpost&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=thewallstreetjournal&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=thewashingtonpost&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=reuters&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);




    }

    public void addCulturalJson(){
        RequestQueue queue= VolleySingleton.getInstance().getRequestQueue();

        JsonObjectRequest newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=entertainmentweekly&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=mashable&sortBy=latest&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=redditrall&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);


    }

    public void addInternationalJson(){
        RequestQueue queue= VolleySingleton.getInstance().getRequestQueue();

        JsonObjectRequest newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=thenewyorktimes&sortBy=popular&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=theguardianuk&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=independent&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=googlenews&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=buzzfeed&sortBy=latest&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);

        newsRequest= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=bbcnews&sortBy=top&apiKey=0f163662966c464f9a7ba800aca9d9e1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplaynewsActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newsRequest);


    }

    private void parseJSON(JSONObject jsonObject){
        if(jsonObject==null||jsonObject.length()==0){
        }
        else{
            try{
                JSONArray newsArticlesArray=jsonObject.getJSONArray("articles");
                for(int i=0;i<newsArticlesArray.length();i++) {
                    JSONObject currentArticle = newsArticlesArray.getJSONObject(i);
                    NewsDetails toBeAdded = new NewsDetails();
                    toBeAdded.setAuthor(currentArticle.getString("author"));
                    toBeAdded.setDate(currentArticle.getString("publishedAt"));
                    toBeAdded.setNewsTitle(currentArticle.getString("title"));
                    toBeAdded.setDescription(currentArticle.getString("description"));
                    toBeAdded.setSource(jsonObject.getString("source"));
                    toBeAdded.setFullStoryUrl(currentArticle.getString("url"));
                    toBeAdded.setImageUrl(currentArticle.getString("urlToImage"));
                    allNews.add(toBeAdded);
                }

            }
            catch (JSONException e){
            }
            ListviewAdapter lva=new ListviewAdapter(allNews,this);
            lv.setAdapter(lva);
        }
    }
}



