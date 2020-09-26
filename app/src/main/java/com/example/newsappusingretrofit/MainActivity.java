package com.example.newsappusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MainArticleAdapter mainArticleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getResponse();


    }

    public void getResponse(){
        Call<ApiResponse> call = jsonPlaceHolderApi.getLatestNews("cnn","7c64e1b4cb01427e837f67cf94b921c3");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse>call, Response<ApiResponse> response) {
                if(response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();
                    if(articleList.size()>0) {
                        mainArticleAdapter = new MainArticleAdapter(articleList);
                        recyclerView.setAdapter(mainArticleAdapter);
                        recyclerView.setLayoutManager(layoutManager);
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResponse>call, Throwable t) {
                Log.e("out", t.toString());


            }
        });
    }
}