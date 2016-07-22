package com.example.ravikiran.newsbin.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.activities.DisplaynewsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FetchnewsFragment extends Fragment {

    CheckBox sports_check;
    CheckBox science_check;
    CheckBox business_check;
    CheckBox culture_check;
    CheckBox international_check;
    Button showNews;

    public FetchnewsFragment() {
    }

    public static FetchnewsFragment getInstance(){
        FetchnewsFragment fragment =new FetchnewsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fetchnews, container, false);
        sports_check=(CheckBox)v.findViewById(R.id.checkBox);
        science_check=(CheckBox)v.findViewById(R.id.checkBox2);
        business_check=(CheckBox)v.findViewById(R.id.checkBox3);
        culture_check=(CheckBox)v.findViewById(R.id.checkBox4);
        international_check=(CheckBox)v.findViewById(R.id.checkBox5);
        showNews=(Button)v.findViewById(R.id.button);
        showNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sports_check.isChecked()||science_check.isChecked()||business_check.isChecked()||culture_check.isChecked()||international_check.isChecked()){
                    Intent i=new Intent(getActivity(), DisplaynewsActivity.class);
                    i.putExtra("sports",sports_check.isChecked());
                    i.putExtra("science",science_check.isChecked());
                    i.putExtra("business",business_check.isChecked());
                    i.putExtra("culture",culture_check.isChecked());
                    i.putExtra("international",international_check.isChecked());
                    startActivity(i);

                }
                else{
                    Toast.makeText(getActivity(), "select something!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;

    }

}
