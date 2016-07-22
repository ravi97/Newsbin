package com.example.ravikiran.newsbin.classes;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ravikiran on 7/19/2016.
 */
public class MyApplication extends Application {

    private static Context myContext=null;
    @Override
    public void onCreate() {
        super.onCreate();
        myContext=getApplicationContext();
    }


    public static Context getAppContext(){
        return myContext;
    }
}
