package com.example.ravikiran.newsbin.classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

/**
 * Created by Ravikiran on 7/19/2016.
 */
public class NewsDetails {
    String author;
    String newsTitle;
    String date;
    String description;
    String imageUrl;
    String fullStoryUrl;
    String source;
    Bitmap imageBitmap;

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public Bitmap byteToBitmap(byte[] imageArray){
        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageArray);
        return BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);

    }

    public byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
        return stream.toByteArray();
    }

    public NewsDetails() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullStoryUrl() {
        return fullStoryUrl;
    }

    public void setFullStoryUrl(String fullStoryUrl) {
        this.fullStoryUrl = fullStoryUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
