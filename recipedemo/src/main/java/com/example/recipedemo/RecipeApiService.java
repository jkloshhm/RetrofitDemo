package com.example.recipedemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 请求接口类：在Mob平台申请的 appkey：25d5a30486298
 *
 * @author jkloshhm  2018-05-18
 */
public interface RecipeApiService {

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

}
