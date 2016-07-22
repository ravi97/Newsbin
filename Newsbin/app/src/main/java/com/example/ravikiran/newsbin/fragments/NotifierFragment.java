package com.example.ravikiran.newsbin.fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.classes.Notifier;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotifierFragment extends Fragment {
    PendingIntent pendingIntent;
    AlarmManager am;
    Boolean isAlarmManager;


    public NotifierFragment() {
        // Required empty public constructor
    }

    public static NotifierFragment getInstance(){
        NotifierFragment fragment=new NotifierFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_notifier, container, false);

        final Button button1=(Button)v.findViewById(R.id.addNotif);
        final Button button2=(Button)v.findViewById(R.id.remove_notif);
        final EditText hours=(EditText)v.findViewById(R.id.editText2);
        final EditText minutes=(EditText)v.findViewById(R.id.editText3);
        final Spinner newsGenre=(Spinner)v.findViewById(R.id.spinner);
        final TextView tv = (TextView)v.findViewById(R.id.notif_status);

        am=(AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);

        SharedPreferences sharedpreference= getActivity().getPreferences(Context.MODE_PRIVATE);
        if(sharedpreference.getBoolean("alarmManagerStatus",false)){
            button1.setEnabled(false);
            String text=sharedpreference.getString("statusText","");
            tv.setText(text);

        }
        else{
            button2.setEnabled(false);
        }



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hours.getText().toString().equals("")||minutes.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "enter a time", Toast.LENGTH_SHORT).show();
                }
                else {
                    int hrs = Integer.parseInt(hours.getText().toString());
                    int mins = Integer.parseInt(minutes.getText().toString());
                    String news = newsGenre.getSelectedItem().toString();
                    if (hrs >= 0 && hrs < 24 && mins >= 0 && mins < 60) {

                        String statusText="set notifier at " + hours.getText().toString() + ":" + minutes.getText().toString() + " for " + news;

                        tv.setText(statusText);
                        button2.setEnabled(true);
                        button1.setEnabled(false);
                        Intent i=new Intent(getActivity(),Notifier.class);
                        i.putExtra("news", news);
                        PendingIntent pendingIntent=PendingIntent.getBroadcast(getActivity(),0,i,0);
                        Calendar calendar=Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hrs);
                        calendar.set(Calendar.MINUTE, mins);
                        isAlarmManager=true;
                        SharedPreferences sharedpreference= getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedpreference.edit();
                        editor.putBoolean("alarmManagerStatus",isAlarmManager);
                        editor.putString("statusText", statusText);
                        editor.apply();
                        am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);




                    } else {
                        Toast.makeText(getActivity(), "enter a valid time", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) getActivity().findViewById(R.id.notif_status);
                tv.setText("");
                button1.setEnabled(true);
                button2.setEnabled(false);
                am.cancel(pendingIntent);
                isAlarmManager=false;
                SharedPreferences sharedpreference= getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedpreference.edit();
                editor.putBoolean("alarmManagerStatus", isAlarmManager);
                editor.putString("statusText", "");
                editor.apply();

            }
        });


        return v;
    }






}
