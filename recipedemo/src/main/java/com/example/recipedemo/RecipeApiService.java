package com.example.recipedemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApiService {

    /**  recipedemo
     key:25d5a30486298  http://apicloud.mob.com/v1/cook/category/query
     接口地址：http://apicloud.mob.com/v1/cook/category/query
     支持格式：JSON
     请求方式：GET
     请求示例：http://apicloud.mob.com/v1/cook/category/query?key=appkey
     */

    @GET("query")
    Call<CookCategoryBean> getCategoryData(@Query("key") String key);

    @GET("query")
    Call<CookMenuByIdBean> getGetMenuFromId(@Query("key") String key, @Query("id") String id);

}
