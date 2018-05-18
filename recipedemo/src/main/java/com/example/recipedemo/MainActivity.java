package com.example.recipedemo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apicloud.mob.com/v1/cook/category/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeApiService recipeApiService = retrofit.create(RecipeApiService.class);
        //25d5a30486298 是在Mob申请的APP key
        Call<CookCategoryBean> gankFuliDataResponse = recipeApiService.getCategoryData("25d5a30486298");
        gankFuliDataResponse.enqueue(new Callback<CookCategoryBean>() {
            @Override
            public void onResponse( Call<CookCategoryBean> call,
                                    final Response<CookCategoryBean> response) {

                String resultsBeanString = response.body().getResult().getCategoryInfo().getName();
                TextView t = findViewById(R.id.cook_body);
                t.setText(resultsBeanString);
                Toast.makeText(MainActivity.this, "加载成功~", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CookCategoryBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "加载失败~", Toast.LENGTH_SHORT).show();
            }
        });


        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://apicloud.mob.com/v1/cook/menu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeApiService recipeApiService1 = retrofit1.create(RecipeApiService.class);
        //25d5a30486298 是在Mob申请的APP key
        Call<CookMenuByIdBean> gankFuliDataResponse1 = recipeApiService1.getGetMenuFromId("25d5a30486298","00100010070000000001");
        gankFuliDataResponse1.enqueue(new Callback<CookMenuByIdBean>() {
            @Override
            public void onResponse( Call<CookMenuByIdBean> call,
                                    final Response<CookMenuByIdBean> response) {

                String resultsBeanString1 = response.body().getResult().getRecipe().getImg();
                ImageView t = findViewById(R.id.cook_body_by_id);
                Glide.with(MainActivity.this).load(resultsBeanString1).into(t);
                Toast.makeText(MainActivity.this, "加载成功1~", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CookMenuByIdBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "加载失败1~", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
