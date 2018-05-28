package com.example.recipedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 主activity
 *
 * @author jkloshhm  2018-05-18
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apicloud.mob.com/v1/cook/category/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiRecipe apiRecipe = retrofit.create(ApiRecipe.class);
        //25d5a30486298 是在Mob申请的APP key
        Call<ResponseBody> gankFuliDataResponse = apiRecipe.getCategoryData("25d5a30486298");
        gankFuliDataResponse.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   final Response<ResponseBody> response) {
                try {

                    //String resultsBeanString = response.body().getResult().getCategoryInfo().getName();
                    String resultsBeanString = new String(response.body().bytes());
                    TextView t = findViewById(R.id.cook_body);
                    t.setText(resultsBeanString);
                    Toast.makeText(MainActivity.this, "加载成功~", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "加载失败~", Toast.LENGTH_SHORT).show();
            }
        });


        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://apicloud.mob.com/v1/cook/menu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiRecipe apiRecipe1 = retrofit1.create(ApiRecipe.class);

        //25d5a30486298 是在Mob申请的APP key
        Call<CookMenuByIdBean> gankFuliDataResponse1 = apiRecipe1.getGetMenuFromId("25d5a30486298", "00100010070000000001");
        gankFuliDataResponse1.enqueue(new Callback<CookMenuByIdBean>() {
            @Override
            public void onResponse(Call<CookMenuByIdBean> call,
                                   final Response<CookMenuByIdBean> response) {

                String resultsBeanString1 = response.body().getResult().getRecipe().getImg();
                ImageView t = findViewById(R.id.cook_body_by_id);
                Glide.with(MainActivity.this).load(resultsBeanString1).into(t);
                Toast.makeText(MainActivity.this, "加载图片成功~", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CookMenuByIdBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "加载图片失败~", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
