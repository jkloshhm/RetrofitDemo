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


        //第一步:创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://gank.io/api/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
        //第二步:创建ApiService对象
        GankApiService gankApiService = retrofit.create(GankApiService.class);

        //第三步:创建call对象，即网络请求对象
        Call<GankFuliDataResponse> gankFuliDataResponse = gankApiService.getCategoryData("福利", 50, 1);

        //第四步:用call对象的.enqueue()方法发送请求，得到数据onResponse()或者onFailure()
        //gankFuliDataResponse.execute();
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

