package com.example.marcin.yndgallery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

    int authorIndexes[];
    String authors[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().setLenient().create();

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

                ListView listView = (ListView) findViewById(R.id.mainList);
                listView.setAdapter(new PhotoInfoAdapter(getApplicationContext(), PhotoInfoAdapter.addAuthorIndex(response.body())));

                authorIndexes = new int[response.body().size()];
                authorIndexes = getAuthorIndexes(PhotoInfoAdapter.addAuthorIndex(response.body()));

                authors = new String[response.body().size()];
                authors = getAuthors(PhotoInfoAdapter.addAuthorIndex(response.body()));
            }

            @Override
            public void onFailure(Call<List<PhotoInfo>> call, Throwable t) {

            }});

        final ListView listView = (ListView) findViewById(R.id.mainList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);

                intent.putExtra("authors", authors);
                intent.putExtra("indexes", authorIndexes);
                intent.putExtra("id", position);

                startActivity(intent);
            }
        });

    }

    public String[] getAuthors(List<PhotoInfo> photoInfoList){

        String[] authors = new String[photoInfoList.size()];

        for(int i = 0; i<photoInfoList.size();i++){
            authors[i] = photoInfoList.get(i).getAuthor();
        }

        return authors;
    }

    public int[] getAuthorIndexes(List<PhotoInfo> photoInfoList){

        int[] indexes = new int[photoInfoList.size()] ;

        for(int i = 0; i<photoInfoList.size();i++){
            indexes[i] = photoInfoList.get(i).getAuthorIndex();
        }

        return indexes;
    }


}

