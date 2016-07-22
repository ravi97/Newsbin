package com.example.ravikiran.newsbin.classes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.activities.DisplaynewsActivity;

/**
 * Created by Ravikiran on 7/21/2016.
 */
public class Notifier extends BroadcastReceiver {

    NotificationManager notificationManager;
    Notification notification;
    Boolean sports,science,business,culture,international;

    @Override
    public void onReceive(Context context, Intent intent) {

        sports=false;
        science=false;
        business=false;
        culture=false;
        international=false;
        Bundle bundle= intent.getExtras();
        String news=bundle.getString("news");

        switch (news){
            case "sports new":
            {
                sports = true;
            }
            case "science and technology news":{
                science=true;
            }
            case "business and politics news":{
                business=true;
            }
            case "culture and recreational news":{
                culture=true;
            }
            case "international news":{
                international=true;
            }
            default:

        }

        Intent toNews=new Intent(context, DisplaynewsActivity.class);
        toNews.putExtra("sports", sports);
        toNews.putExtra("science", science);
        toNews.putExtra("business", business);
        toNews.putExtra("culture", culture);
        toNews.putExtra("international", international);
        PendingIntent pi=PendingIntent.getActivity(
                context, 0, toNews, 0
        );

        notification=new NotificationCompat.Builder(context)
                .setContentTitle("test notification")
                .setContentText("your "+news+" is ready!")
                .setSmallIcon(R.mipmap.newsbin_logo)
                .setContentIntent(pi)
                .setTicker("aaa")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .build();
        notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);


    }
}
