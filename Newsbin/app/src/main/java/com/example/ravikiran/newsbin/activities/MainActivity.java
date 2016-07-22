package com.example.ravikiran.newsbin.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.example.ravikiran.newsbin.R;
import com.example.ravikiran.newsbin.adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager viewPager;

    private StringBuffer help=new StringBuffer("This app has 3 main features :" +
            "\nRead news: Go to read news tab if you want to read news now.\nJust select the news type you want and start reading" +
            "\nRead later: Not in the mood right now? Just set the time and your favourite news will be delivered\nright to your notification bar" +
            "\nRead offline: You can store your favourite articles to your phone and read it anywhere,anytime!!");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager=(ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        tabs=(PagerSlidingTabStrip)findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            showHelp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showHelp(){
        final AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("glad to help you!!!");
        alert.setMessage(help);
        alert.setCancelable(false);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }
}
