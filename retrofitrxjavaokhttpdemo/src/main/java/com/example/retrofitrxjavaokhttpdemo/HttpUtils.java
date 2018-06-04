package com.example.retrofitrxjavaokhttpdemo;

import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * PS: HttpUtils网络请求类
 *
 * @author jack.guo,  Date on 2018/6/4.
 */
public class HttpUtils {
    private static final String BASE_URL = "http://apicloud.mob.com/v1/cook/menu/";
    private Retrofit retrofit;
    //7天 无网超时时间
    private static final int NO_NET_MAX = 60 * 60 * 24 * 7;
    //30秒  有网超时时间
    private static final int NET_MAX = 30;

    private HttpUtils() {
        Interceptor mInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtils.networkIsAvailable(MyApplication.getContext())) {
                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "private, only-if-cached, max-stale=" + NO_NET_MAX)
                            .build();
                } else {
                    request = request.newBuilder()
                            //Pragma:no-cache。在HTTP/1.1协议中，它的含义和Cache-Control:no-cache相同。为了确保缓存生效
                            .removeHeader("Pragma")
                            //添加缓存请求头
                            .header("Cache-Control", "private, max-age=" + NET_MAX)
                            .build();
                }

                return chain.proceed(request);
            }
        };

        OkHttpClient mClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(mInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(new Cache(new File(MyApplication.getContext().getCacheDir() + "http"), 1024 * 1024 * 10))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build();
    }

    /**
     * 单例设计模式
     */
    private static class SingleRetrofit {
        private static final HttpUtils HTTP_UTILS_INSTANCE = new HttpUtils();
    }

    private static HttpUtils getInstance() {
        return SingleRetrofit.HTTP_UTILS_INSTANCE;
    }

    public static ApiServiceInterFace createApiService() {
        //GetWeatherService getWeatherService = getInstance().retrofit.create(GetWeatherService.class);
        return getInstance().retrofit.create(ApiServiceInterFace.class);
    }

    /**
     * 执行网络请求
     *
     * @param observable
     * @param listener
     * @param <T>
     */
    public static <T> void request(Observable<T> observable, final IResponseListener<T> listener) {

        if (!NetWorkUtils.networkIsAvailable(MyApplication.getContext())) {
            //ToastUtils.getInstance().showToast("网络不可用,请检查网络");
            Toast.makeText(MyApplication.getContext(), "网络不可用,请检查网络", Toast.LENGTH_SHORT).show();
            if (listener != null) {
                listener.onFail();
            }
            return;
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   //LogUtils.d("onError", e.getMessage());
                                   if (listener != null) {
                                       listener.onFail();
                                   }
                               }

                               @Override
                               public void onComplete() {

                               }

                               @Override
                               public void onSubscribe(Disposable disposable) {

                               }

                               @Override
                               public void onNext(T data) {
                                   if (listener != null) {
                                       listener.onSuccess(data);
                                   }
                               }
                           }
                );


    }

    public interface IResponseListener<T> {
        /**
         * 请求成功回调
         *
         * @param data 请求返回的bean
         */
        void onSuccess(T data);

        /**
         * 请求失败
         */
        void onFail();
    }

}
