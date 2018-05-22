package com.example.jkloshhm.retrofitdemo;

import com.example.jkloshhm.retrofitdemo.GankFuliDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author jkloshhm 2018-05-16
 */
public interface GankApiService {

    /**
     * 根据category获取Android、iOS等干货数据
     * 接口请求示例：http://gank.io/api/data/{category}/{count}/{page}
     *
     * @param category 类别
     * @param count    条目数目
     * @param page     页数
     * @return GankFuliDataResponse
     */
    @GET("data/{category}/{count}/{page}")
    Call<GankFuliDataResponse> getCategoryData(@Path("category") String category, @Path("count") int count, @Path("page") int page);
}
