package com.example.ravikiran.newsbin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.classes.NewsDetails;

import java.util.ArrayList;

/**
 * Created by Ravikiran on 7/19/2016.
 */
public class ListviewAdapter extends BaseAdapter {


    ArrayList<NewsDetails> toBeEntered;
    Context context;
    LayoutInflater inflater=null;

    public ListviewAdapter(ArrayList<NewsDetails> toBeEntered, Context context) {
        this.toBeEntered = toBeEntered;
        this.context = context;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return toBeEntered.size();
    }

    @Override
    public Object getItem(int position) {
        return toBeEntered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowview=inflater.inflate(R.layout.listview_element,null);
        TextView title=(TextView)rowview.findViewById(R.id.newsTitle);
        title.setText(toBeEntered.get(position).getNewsTitle());
        TextView source=(TextView)rowview.findViewById(R.id.newsSource);
        source.setText(toBeEntered.get(position).getSource());
        TextView date=(TextView)rowview.findViewById(R.id.newsDate);
        date.setText(toBeEntered.get(position).getDate());

        return rowview;
    }
}
