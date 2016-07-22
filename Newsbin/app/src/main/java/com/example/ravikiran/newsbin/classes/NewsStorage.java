package com.example.ravikiran.newsbin.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ravikiran on 7/20/2016.
 */
public class NewsStorage extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="news.db";
    private static final String TABLE_NAME="myNews";
    private static final int DATABASE_VERSION=1;
    private static final String COLUMN_TITLE="TITLE";
    private static final String COLUMN_SOURCE="SOURCE";
    private static final String COLUMN_AUTHOR="AUTHOR";
    private static final String COLUMN_DATE="DATE";
    private static final String COLUMN_IMAGEURL="IMAGEURL";
    private static final String COLUMN_DESC="DESCRIPTION";
    private static final String COLUMN_STORY="STORY";
    private static final String COLUMN_IMAGE="IMAGE";

    public NewsStorage(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_TITLE + " TEXT," +
                COLUMN_SOURCE + " TEXT," +
                COLUMN_AUTHOR+" TEXT,"+
                COLUMN_DATE+" TEXT,"+
                COLUMN_IMAGEURL+" TEXT,"+
                COLUMN_DESC+" TEXT,"+
                COLUMN_IMAGE+" BLOB,"+
                COLUMN_STORY + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public void addRow(NewsDetails toBeadded){
        ContentValues values=new ContentValues();
        values.put(COLUMN_TITLE,toBeadded.getNewsTitle());
        values.put(COLUMN_SOURCE,toBeadded.getSource());
        values.put(COLUMN_AUTHOR,toBeadded.getAuthor());
        values.put(COLUMN_DATE,toBeadded.getDate());
        values.put(COLUMN_IMAGEURL,toBeadded.getImageUrl());
        values.put(COLUMN_DESC,toBeadded.getDescription());
        values.put(COLUMN_IMAGE,toBeadded.bitmapToByte(toBeadded.getImageBitmap()));
        values.put(COLUMN_STORY,toBeadded.getFullStoryUrl());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteRow(String title){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_TITLE + "=\"" + title + "\";");
    }

    public Cursor returnFullTableCursor(){
        Cursor c;
        SQLiteDatabase db=getWritableDatabase();
        c=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return c;
    }
}
