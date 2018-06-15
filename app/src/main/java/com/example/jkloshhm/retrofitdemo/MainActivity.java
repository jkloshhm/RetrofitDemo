package com.example.jkloshhm.retrofitdemo;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jkloshhm 2018-05-16
 */
public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabButton;
    private ImageView imageView;
    private List<GankFuliDataResponse.ResultsBean> resultsBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabButton = findViewById(R.id.fab);
        imageView = findViewById(R.id.image);
        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://gank.io/api/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
        GankApiService gankApiService = retrofit.create(GankApiService.class);
        //50代表请求50条数据
        Call<GankFuliDataResponse> gankFuliDataResponse = gankApiService.getCategoryData("福利", 50, 1);
        gankFuliDataResponse.enqueue(new Callback<GankFuliDataResponse>() {
            @Override
            public void onResponse(@Nullable Call<GankFuliDataResponse> call,
                                   @Nullable final Response<GankFuliDataResponse> response) {
                resultsBeanList = response.body().getResults();
                if (resultsBeanList != null) {
                    String imgageurl = resultsBeanList.get(0).getUrl();
                    Glide.with(MainActivity.this).load(imgageurl).into(imageView);
                    //点击按钮显示50张图片中随机的一张
                    fabButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Random random = new Random();
                            int i = random.nextInt(50);
                            Log.i("jkl", "随机数" + i);
                            String imgageurl = resultsBeanList.get(i).getUrl();
                            Glide.with(MainActivity.this).load(imgageurl).into(imageView);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GankFuliDataResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

