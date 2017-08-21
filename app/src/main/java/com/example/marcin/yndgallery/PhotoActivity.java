package com.example.marcin.yndgallery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Intent intent = getIntent();

        Integer id = intent.getIntExtra("id", -1);
        String author = intent.getStringExtra("author");
        String index = intent.getStringExtra("index");

        PhotoView photoView = (PhotoView) findViewById(R.id.photoDetailView);
        getSupportActionBar().hide();

        Picasso.with(this).load("https://unsplash.it/500?image=" + id).into(photoView);

        TextView label = (TextView) findViewById(R.id.photoDetailDescription);
        label.setText(author +" #"+ index);

    }
}
