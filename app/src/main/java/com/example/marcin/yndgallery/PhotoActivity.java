package com.example.marcin.yndgallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        PhotoView photoView = (PhotoView) findViewById(R.id.photoDetailView);

        //Picasso.with(mcontext).load("https://unsplash.it/100/100?image=" + position).into(imageView);

        //TODO full screen?

        //TODO przekazywanie info w intecji

        //TODO wyświetlić info z intecji

        Picasso.with(this).load("https://unsplash.it/1000?image=0").into(photoView);
    }
}
