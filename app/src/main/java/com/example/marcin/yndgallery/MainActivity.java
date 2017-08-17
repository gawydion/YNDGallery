package com.example.marcin.yndgallery;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().setLenient().create();

        // Retrofit - obsługuje zapytania HTTP
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitAPI retrofitAPI =
                retrofit.create(RetrofitAPI.class);

        Call<List<PhotoInfo>> call = retrofitAPI.loadList();
        call.enqueue(new Callback<List<PhotoInfo>>() {
            @Override
            public void onResponse(Call<List<PhotoInfo>> call, Response<List<PhotoInfo>> response) {

                Toast.makeText(getApplicationContext(), response.body().get(0).getAuthor().toString(), Toast.LENGTH_LONG).show();

                ListView listView = (ListView) findViewById(R.id.mainList);
                listView.setAdapter(new PhotoInfoAdapter(getApplicationContext(), response.body()));

            }

            @Override
            public void onFailure(Call<List<PhotoInfo>> call, Throwable t) {

            }});



    }

}

