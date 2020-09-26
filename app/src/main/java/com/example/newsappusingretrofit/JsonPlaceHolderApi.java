package com.example.newsappusingretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("top-headlines")
    Call<ApiResponse> getLatestNews(@Query("sources") String source, @Query("apiKey") String apiKey);
}
