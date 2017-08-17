package com.example.marcin.yndgallery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Marcin on 17.08.2017.
 */

public interface RetrofitAPI {

    public static final String BASE_URL = "https://unsplash.it/";

    @GET("list/")
    Call<List<PhotoInfo>> loadList();
}
