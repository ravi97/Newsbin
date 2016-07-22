package com.example.ravikiran.newsbin.classes;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ravikiran on 7/19/2016.
 */
public class VolleySingleton {

    private static VolleySingleton ourInstance = null;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private VolleySingleton() {
        requestQueue= Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader=new ImageLoader(requestQueue,
                new ImageLoader.ImageCache(){

                    private LruCache<String,Bitmap> cache=new LruCache<String,Bitmap>(50);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url,bitmap);
                    }
                });
    }
    public static VolleySingleton getInstance() {
        if(ourInstance==null){
            ourInstance=new VolleySingleton();
        }
        return ourInstance;
    }
    public RequestQueue getRequestQueue(){

        return requestQueue;
    }
    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
