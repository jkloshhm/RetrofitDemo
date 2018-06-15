package com.example.recipedemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 请求接口：在Mob平台申请的 appkey：25d5a30486298
 *
 * @author jkloshhm  2018-05-18
 */
public interface ApiRecipe {

    /**
     * 菜谱分类标签查询：查询菜谱的所有分类。
     * 请求示例：http://apicloud.mob.com/v1/cook/category/query?key=appkey
     *
     * @param key appkey
     * @return ResponseBody
     */
    @GET("query")
    Call<ResponseBody> getCategoryData(@Query("key") String key);


    /**
     * 菜谱查询接口：根据菜谱ID查询菜谱详情。
     * 请求示例：http://apicloud.mob.com/v1/cook/menu/query?key=appkey&id=00100010070000000001
     *
     * @param key appkey
     * @param id  菜谱id
     * @return CookMenuByIdBean
     */
    @GET("query")
    Call<CookMenuByIdBean> getGetMenuFromId(@Query("key") String key, @Query("id") String id);


    /**
     * 根据category获取Android、iOS等干货数据
     * 接口请求示例：http://gank.io/api/data/{category}/{count}/{page}
     *
     * @param category 类别
     * @param count    条目数目
     * @param page     页数
     * @return ResponseBody
     */
    @GET("data/{category}/{count}/{page}")
    Call<ResponseBody> getCategoryData(@Path("category") String category, @Path("count") int count, @Path("page") int page);

}
