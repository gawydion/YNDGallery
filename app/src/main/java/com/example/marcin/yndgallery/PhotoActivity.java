package com.example.marcin.yndgallery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.OnSingleFlingListener;
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

    Integer id;// = intent.getIntExtra("id", -1);
    String authors[];// = intent.getStringArrayExtra("authors");
    int[] authorIndexes;// = intent.getIntArrayExtra("indexes");
    int[] widths;// = intent.getIntArrayExtra("widths");
    int[] heights;// = intent.getIntArrayExtra("heights");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", -1);
        authors = intent.getStringArrayExtra("authors");
        authorIndexes = intent.getIntArrayExtra("indexes");
        widths = intent.getIntArrayExtra("widths");
        heights = intent.getIntArrayExtra("heights");

        final int maxId = authors.length;

        getSupportActionBar().hide();

        setPhoto(id);

        PhotoView photoView = (PhotoView) findViewById(R.id.photoDetailView);

        photoView.setOnSingleFlingListener(new OnSingleFlingListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                //https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures

                final int SWIPE_THRESHOLD = 100;
                final int SWIPE_VELOCITY_THRESHOLD = 100;

                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {

                                id--;
                                if (id == -1) id = maxId;
                                setPhoto(id);

                            } else {

                                id++;
                                if (id == maxId + 1) id = 0;
                                setPhoto(id);

                            }
                            result = true;
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        });
    }

    public void setPhoto(int newId) {

        PhotoView photoView = (PhotoView) findViewById(R.id.photoDetailView);

        Picasso.with(this).load("https://unsplash.it/500?image=" + newId).into(photoView);

        TextView label = (TextView) findViewById(R.id.photoDetailDescription);
        label.setText(authors[newId] + " #" + authorIndexes[newId] + " Size: " + widths[newId] + "x" + heights[newId]);

    }
}
